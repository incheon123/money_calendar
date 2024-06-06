import {History} from "./history.js";
import {bindClickEventOnElement} from "./calendar.js";
import {getRid,  getCurrentRoomUrl} from "./url/url.js";
import {getHistoryInfoWhenClickSubmitBtn, empty, getSelectedDate} from "./module.js";
import {setHistory, updateHistorys, deleteHistorys, saveHistorys} from "./ajax.js";

const DATES = document.getElementsByClassName('actualDate');

/**
 * 초기화 할 때 가져온 데이터를 저장한 배열
 */
let datesHistoryList = [];

/**
 * datesHistoryList을 빈 배열로 만드는 함수
 */
export function emptyDatesHistoryList()
{
    datesHistoryList = [];
}

/**
 * insertHistoryToCalendar 함수에서 임시로 쓰는 변수
 * 각 날짜에 해당하는 소득/지출 기록, 소득합계, 지출합계 정보를 임시적으로 가지는 변수
 * {
*           history : [],
            incomeSum : 0,
            outcomeSum : 0
 * }
            초기에는 빈 배열이지만 해당 함수에서 사용시 위와 같은 구조로 만들어 사용

 */
let datesHistoryObj = {}
let incomeTotal = 0;            // 각 날짜별 소득 합계
let outcomeTotal = 0;           // 각 날짜별 지출 합계

/**
 * 처음 달력을 초기화 할 때 사용하는 초기화 함수
 * 1 ~ 31일 까지 반복하면서 각 날짜별 정보를 datesHistoryObj에 임시적으로 담고
 * datesHistoryList에 넣어줌으로써 동적으로 활용
 * 그리고 그 변수를 이용하여 각 날짜에 맞게 뿌려줌
 * 
 * 반복문이 끝나고 총 해당 월의 총 소득/지출 합계를 계산
 * @param {*} historys 
 */
export function insertHistoryToCalendar(historys){
    
    console.log("insertHistoryToCalendar.......................")
    
    datesHistoryList = []
    incomeTotal = 0;
    outcomeTotal = 0;
    for(let i = 1; i <= 31; i++){

        datesHistoryObj = {
            history : [],
            incomeSum : 0,
            outcomeSum : 0
        };

        historys.forEach( (history) => {
            
            if(history.date == i)
            {
                datesHistoryObj.history.push(history);
                if(history.type === 'income') datesHistoryObj.incomeSum += history.money;
                else                          datesHistoryObj.outcomeSum += history.money;
            }
        });

        if(datesHistoryObj.history.length > 0)
        {
            datesHistoryList[i] = datesHistoryObj;
            initHistoryInDate(i, datesHistoryObj);
        }     
    }
    console.log(datesHistoryList)
    initTotalIncomeAndOutcome(datesHistoryList);
}


/**
 * 각 날짜에서 소득/지출에 해당하는 날짜에 뿌려줌
 * @param {*} historyObj 
 */
export function initHistoryInDate(date, historyObj){
    console.log("hi => ", historyObj);
    DATES[date - 1].append(createIncomeMoney(historyObj.incomeSum));
    DATES[date - 1].append(createOutcomeMoney(historyObj.outcomeSum));

}

/**
 * initHistoryInDate 함수에서 날짜별 총 소득/지출을 넘겨주면 실질적으로
 * html 요소를 만들어서 반환해줌
 * @param {*} m 
 * @returns 
 */
function createIncomeMoney(m){
    let income = document.createElement("p");
    income.innerText = `+${m}원`
    income.className = "blue money";
    
    return income;
}
function createOutcomeMoney(m){
    let outcome = document.createElement("p");
    outcome.innerText = `-${m}원`
    outcome.className = "red money";

    return outcome;
}

/**
 * datesHistoryList에 historyObj를 추가하는 함수
 * @param {*} historyObj 
 */
export function addHistoryObj(historyObj)
{
    let i = historyObj.type == 'income' ? 1 : 2;
    if(datesHistoryList[historyObj.date] == null){
        
        datesHistoryList[historyObj.date] = {
            history: historyObj,
            incomeSum : 0,
            outcomeSum : 0
        }
        
    }
    let specifiedDateInfo = datesHistoryList[historyObj.date];
    console.log(specifiedDateInfo)
    
    let op1 = historyObj.money;
    let op2 = i == 1 ? specifiedDateInfo.incomeSum : specifiedDateInfo.outcomeSum;
    let result = addMoney(op1, op2);
    let operator = i == 1 ? '+' : '-';

    updateDate(historyObj.date, i, operator, result);
    if(i == 1)
        datesHistoryList[historyObj.date].incomeSum = result;
    else
        datesHistoryList[historyObj.date].outcomeSum = result;
}

/**
 * 각 날짜에 저장되어 있는 기록을 변경했을 시 아래 함수들 실행
 * 1. 소득/지출 타입 변경시 
 * 2. 금액 변경시
 * 3. 내용 변경시
 * 
 * 고려해야 될 사항은 다음과 같다
 * 1. 금액은 동일하나 타입 변경시
 *  1.1 소득 -> 지출이면 소득일 때의 금액 전부를 지출로 변경
 *  1.2 지출 -> 소득 또한 마찬가지
 *  
 * 2. 타입은 동일하나 금액 변경시
 *  2.1 날짜 총합 변경하고 총 합계 변경
 *  2.2 차이값을 해당 합계에 더함
 * 
 * 3. 금액, 타입 둘 다 변경시
 */



/**
 * 데이터의 기록이나 금액을 변경했을 때 각 날짜의 총 소득/지출 합계를 구하고
 * 총 소득/지출 합계도 구하는 함수
 * @param {*} historyObj 
 */
export function updateHistoryObj(historyObj){
    /**
     * content_no을 찾고 그 객체에 속한 값을 바꿔치기한다.
     */
    let date       = historyObj.date;
    let content_no = historyObj.content_no;
    
    let willUpdatedObj = findHistory(date, content_no);
    let oldValue = willUpdatedObj.money;
    let newValue = historyObj.money;
    let newIncomeSum;
    let newOutcomeSum;
    let oldType = willUpdatedObj.type;
    let newType = historyObj.type;
    console.log(historyObj);
    if(historyObj.type == 'income')
    {
        newIncomeSum = updateIncomeSum(date, oldValue, newValue, oldType, newType, willUpdatedObj);
        // updateIncomeTotal(newIncomeSum);
        console.log('income dlqslek')
    }else
    {
        newOutcomeSum = updateOutcomeSum(date, oldValue, newValue, oldType, newType, willUpdatedObj);
        // updateOutcomeTotal(newOutcomeSum);
    }

    willUpdatedObj.money = newValue
    willUpdatedObj.content = historyObj.content;
    willUpdatedObj.type = historyObj.type;

    console.log(datesHistoryList);
    removeDateElement(date);
    initHistoryInDate(date, datesHistoryList[date]);
}

function updateIncomeSum(date, oldV, newV, oldType, newType, willUpdatedObj)
{

    if(oldType != newType && oldV != newV)
    {
        subOutcomeSum(date, oldV);
        addIncomeSum(date, newV);

        subOutcomeTotal(oldV);
        addIncomeTotal(newV);

        document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
        document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
        return;
    }
    
    if(oldType != newType)          // 타입 불일치
    {
        willUpdatedObj.type = newType;
        calculateIncomeSum(date, newV);
        document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
        document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();

        return;
    }else if(newV != oldV)          // 값 불일치
    {
        let diff
        if(newV > oldV)
        {
            diff = newV - oldV;
            addIncomeSum(date, diff);
            addIncomeTotal(diff);
            document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
            return;
        }
        
        diff = oldV - newV;
        subIncomeSum(date, diff);
        subIncomeTotal(diff);
        document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
        return;
    }else{                          // 타입, 값 아무것도 변경 안되었다면
        return;
    }
}

function updateOutcomeSum(date, oldV, newV, oldType, newType, willUpdatedObj)
{
    if(oldType != newType && oldV != newV)
    {
        subIncomeSum(date, oldV);
        addOutcomeSum(date, newV);

        subIncomeTotal(oldV);
        addOutcomeTotal(newV);

        document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
        document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
        return;
    }

    if(oldType != newType)
    {

        willUpdatedObj.type = newType;
        calculateOutcomeSum(date, newV);

        document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
        document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
        return;
    }else if(newV != oldV)
    {
        let diff
        if(newV > oldV)
        {
            diff = newV - oldV;
            addOutcomeSum(date, diff);
            addOutcomeTotal(diff);
            document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
            return;
        }
        
        diff = oldV - newV;
        subOutcomeSum(date, diff);
        subOutcomeTotal(diff);
        document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
        return;
    }else
    {
        return;
    }
}

function addIncomeSum(date, newV){datesHistoryList[date].incomeSum += newV;}
function subIncomeSum(date, newV){datesHistoryList[date].incomeSum -= newV;}
function addOutcomeSum(date, newV){datesHistoryList[date].outcomeSum += newV;}
function subOutcomeSum(date, newV){datesHistoryList[date].outcomeSum -= newV;}
function addIncomeTotal(newV){incomeTotal += newV;}
function subIncomeTotal(newV){incomeTotal -= newV;}
function addOutcomeTotal(newV){outcomeTotal += newV;}
function subOutcomeTotal(newV){outcomeTotal -= newV;}

function calculateIncomeSum(date, newV)
{
    addIncomeSum(date, newV);
    subOutcomeSum(date, newV);

    addIncomeTotal(newV);
    subOutcomeTotal(newV);
}
function calculateOutcomeSum(date, newV)
{
    subIncomeSum(date, newV);
    addOutcomeSum(date, newV);

    subIncomeTotal(newV);
    addOutcomeTotal(newV);
}

/**
 * 달력정보를 삭제하는 함수
 * @param {*} historyObj 
 */
export function deleteHistoryObj(historyObj)
{
    let date       = historyObj.date;
    let content_no = historyObj.content_no;
    let money      = historyObj.money;
    let originalType = findHistory(date, content_no).type;
    if(originalType == 'income')
    {
        subIncomeSum(date, money)
        subIncomeTotal(money)
        
    }else
    {
        subOutcomeSum(date, money);
        subOutcomeTotal(money);
    }

    document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
    document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
    removeDateElement(date);
    initHistoryInDate(date, datesHistoryList[date]);
    deleteHistory(date,content_no);
    return;
}

/**
 * 실질적 기록 삭제 함수
 * @param {*} date 
 * @param {*} content_no 
 */
function deleteHistory(date, content_no)
{
    let h_list = datesHistoryList[date].history;
    for(let i = 0; i < h_list.length; i++)
        {
            if(h_list[i].content_no == content_no)
            {
                h_list[i] = null;
            }
        }

    
}

/**
 * datesHistoryList에 저장되어 있는 기록을 찾는 함수
 * @param {*} date 
 * @param {*} content_no 
 * @returns 
 */
function findHistory(date, content_no){
    let h_list = datesHistoryList[date].history;
    for(let i = 0; i < h_list.length; i++)
    {
        if(h_list[i].content_no == content_no)
        {
            return h_list[i];
        }
    }

    return -1;
}

function removeDateElement(date){
    $(`.actualDate.d_${date}`).children()[1].remove();
    $(`.actualDate.d_${date}`).children()[1].remove();
}

function addMoney(a, b){
    return a + b;
}
function updateDate(date, i, op, value){
    let parent =  DATES[ date - 1 ];
    if(parent.children[i] == null)
    {
        if(i == 1)
        {
            parent.append(createIncomeMoney(value));
            parent.append(createOutcomeMoney(0));
            return
        }
        parent.append(createIncomeMoney(0));
        parent.append(createOutcomeMoney(value));
        return;
    }
    DATES[ date - 1 ].children[i].innerText = `${op}${value}원`;
}

/**
 * 처음 총 수입/지출 금액 설정 메서드
 * @param {*} historys 
 */
export function initTotalIncomeAndOutcome(historys){

    console.log(historys.length)
    historys.forEach( (h) => {
        incomeTotal  += Number(h.incomeSum);
        outcomeTotal += Number(h.outcomeSum);
    })
    document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
    document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
}
/**
 * 달력 위쪽에 토탈 값 설정하는 메서드
 * @param historys
 */
export function updateTotalIncomeAndOutcome(historyObj){

    if(historyObj.type == 'income')
    {
        
        addTotalIncomeAndIncome(historyObj.money);
    }else
    {
        addTotalOutcomeAndOutcome(historyObj.money);
    }

    document.getElementsByClassName('income-money')[0].innerText = getTotalIncome();
    document.getElementsByClassName('outcome-money')[0].innerText = getTotalOutcome();
}

/**
 * 현재 총 소득과 새로 기입한 소득을 더하는 함수
 * @param {*} money 
 */
function addTotalIncomeAndIncome(money)
{
    money += getTotalIncome();
    setIncomeTotal(money);
    console.log(money);

}

/**
 * 현재 총 지출과 새로 기입한 지출을 더하는 함수
 * @param {*} money 
 */
function addTotalOutcomeAndOutcome(money)
{
    money += getTotalOutcome();
    setOutcomeTotal(money);
    console.log(money);

}

function setIncomeTotal(money){
    incomeTotal = money;
}
function setOutcomeTotal(money){
    outcomeTotal = money;
}
function getTotalIncome(){
    return incomeTotal;
}
function getTotalOutcome(){
    return outcomeTotal;
}
/**
 * <p>저번달/다음달 버튼을 눌렀을 때 처음 화면에 들어왔을 때 실행되는 함수</p>
 * history를 가져오고, history 지출 기록을 표시한다<br>
 */
export function initCalendar(type){
    console.log(type);
    if(type === "PRIVATE")
        setHistory(getRid(getCurrentRoomUrl()));

    if(type === "CHATTING")
        setHistory(getRid(getCurrentRoomUrl()));

    //기존 달력 내용 지움
    empty($(".historys_container"));

}

/**
 * 제한 금액 요소에 값을 집어넣는다<br>
 */
export function setLimitMoney(lm){
    let result = lm;
    let ele = document.getElementsByClassName("limit-money")[0];
    if(result[0] !== null)
        ele.innerText = result[0];
    else{
        //modal
        ele.innerText = "???";
    }
}

/**
 * 페이지가 로드되면 한 번 실행된다<br>
 * 1. 먼저 기존에 .actualDate에 있는 text들을 삭제한다.<br>
 * 2. 삭제하면 수입/지출 금액을 빨강/파랑 색상으로 구분해서 달력(.actualDate)에 삽입한다<br>
 * 3. dates_obj에 data를 집어넣는다.<br>
 */
function bindDynamicalClickEventOnElement(types, selector, event) {
    $(document).on(types, selector, event);
}

export function showDateHistory(date){
    let h = datesHistoryList[date];
    console.log(h);
    if(h != null)
    {
        getSelectedDate(h.history);
        return;
    }
    empty($(document.getElementsByClassName("historys_container")[0]));
}


/**
 * 메인함수
 */
function main(){
    // initCalendar();

    /**
     * 수정버튼을 클릭시 수정 요청을 보냄
     * private랑 chatting방 분리해야됨
     */
    bindDynamicalClickEventOnElement('click', '.modify-historys', function (e) {
        let obj = getHistoryInfoWhenClickSubmitBtn(e);
        let rid = getRid(getCurrentRoomUrl());
        console.log(rid);
        obj['rid'] = rid;

        updateHistorys(obj);
    })

    bindDynamicalClickEventOnElement('click', '.delete-history', function(e){
        let obj = getHistoryInfoWhenClickSubmitBtn(e);
        let rid = getRid(getCurrentRoomUrl());
        obj['rid'] = rid;

        deleteHistorys(obj, e);
    })

    /**
     * 저장버튼을 클릭하면 저장요청을 보냄
     * private랑 chatting방 분리해야됨
     */
    bindClickEventOnElement('.submit-content', () => {

        let history = new History();
        let result = history.checkValidate();
        if(result.result === true) {
            saveHistorys(history, getRid(getCurrentRoomUrl()));
            return;
        }

        alert(result.err)

    })
}

main();