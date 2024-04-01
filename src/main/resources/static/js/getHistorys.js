import {bindClickEventOnElement} from "./calendar.js";
import {emptyMoney, getHistoryInfoWhenClickSubmitBtn, getSelectedDate} from "./module.js";
import {empty} from "./module.js";
import {getHistory, updateHistorys, deleteHistorys, saveHistorys, getLimitMoney} from "./ajax.js";
import {setDateToNextMonth, setDateToPreMonth} from "./date.js";
import {History} from "./general.js";
import {activeModal} from "./modal.js";

const DATES = document.getElementsByClassName('actualDate');

/**
 * stack에 같은 날짜의 객체만 넣고<br>
 * 만일 next date가 stack에 들어있는 객체의 날짜와 다르다면 화면에 출력하고 stack을 비운다<br>
 * @param historys
 */
export function insertHistoryToCalendar(historys){
    console.log("insertHistoryToCalendar.......................")

    document.getElementsByClassName('income-money')[0].innerText = dates_obj['income']
    document.getElementsByClassName('outcome-money')[0].innerText = dates_obj['outcome']

    let stack = [];
    for(let i = 0 ; i < historys.length + 1; i++){
        //stack이 비어있다면 stack에 push한다
        if(stack.length <= 0) {
            stack.push(historys[i]);
            continue;
        }

        //두 번째 히스토리부터 본격적으로 시작한다
        /**
         * stack의 첫 번째 date와 date가 같다면
         * stack에 historys(n)을 추가한다.
         */
        if(i !== historys.length && historys[i].date === stack[0].date){
            stack.push(historys[i]);
        }else{
            /**
             *
             */
            let incomeSum = 0;
            let outcomeSum = 0;

            let target;
            for(let k = 0; k < stack.length; k++){
                target = stack[k];
                if(target.type === 'income'){
                    incomeSum += Number(target.money);
                }
                if(target.type === 'outcome'){
                    outcomeSum += Number(target.money);
                }
            }


            let income = document.createElement("p");
            income.innerText = `+${incomeSum}원`
            income.className = "blue money";

            let outcome = document.createElement("p");
            outcome.innerText = `-${outcomeSum}원`
            outcome.className = "red money";

            DATES[ (stack[0].date) - 1 ].append(income);
            DATES[ (stack[0].date) - 1 ].append(outcome);

            stack = [];
            stack.push(historys[i]);
        }
    }
}


/**
 * history객체가 담긴다 ( date : historys object )<br>
 * @type { {} }
 */
let dates_obj = {};

/**
 * 지출기록에서 확인할 객체들을 dates_obj 객체에 담는다. 이때 날짜를 키값으로 한다.<br>
 * @param historys
 */
export function insertHistorys(historys){
    dates_obj = {}
    let key;
    let incomeSum = 0;
    let outcomeSum = 0;

    for(let i = 0 ; i < historys.length; i++){
        /**
         * key값을 저장한다
         */
        key = historys[i].date;
        if(dates_obj[key] == null) dates_obj[key] = [];

        /**
         * 총 income을 계산한다
         */
        if(historys[i].type === "income")
            incomeSum += Number(historys[i].money);

        /**
         * 총 outcome을 계산한다
         */
        if(historys[i].type === "outcome"){
            outcomeSum += Number(historys[i].money);
        }

        dates_obj[key].push(historys[i]);
    }

    /**
     * income, outcome을 dates_obj 객체에 삽입한다.
     * @type {number}
     */
    dates_obj['income'] = incomeSum;
    dates_obj['outcome'] = outcomeSum;
}

function getIncome(){
    return dates_obj['income']
}
function getOutcome(){
    return dates_obj['outcome']
}

/**
 * <p>저번달/다음달 버튼을 눌렀을 때 처음 화면에 들어왔을 때 실행되는 함수</p>
 * history를 가져오고, history 지출 기록을 표시한다<br>
 */
function bindClickEventOnElementWhichIsActualDate() {
    getHistory();
    bindClickEventOnElement('.actualDate', function (e) {

        getLimitMoney().then(
            function(success){
                let target_date = e.currentTarget.attributes.value.value;
                let obj = dates_obj[target_date];
                if (obj !== undefined) {
                    getSelectedDate(obj);
                }else {
                    empty(document.getElementsByClassName("historys_container")[0]);
                }
            },
            function(error){dates_obj = {};}
        )

    })
}

/**
 * 저번 달/다음 달 버튼에 이벤트를 부여한다<br>
 * 하나는 달력 내용(history)를 비운다<br>
 * 또 다른 하나는 달력을 클릭하면 bindClickEventOnElementWhichIsActualDate 함수를 호출한다.<br>
 * @param ele
 */
function bindClickEventOnElementWhichIsPreOrNextBtn(ele){
    bindClickEventOnElement(ele, function(e){
        if(ele === '.pre-month') setDateToPreMonth();
        else setDateToNextMonth();

        getLimitMoney().then(
            function(success){
            },
            function(error){
                emptyMoney();
                empty($('.input-groups'))
                activeModal();
            }
        )

        empty($(".historys_container"));
        bindClickEventOnElementWhichIsActualDate();
    })
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
/**
 * 메인함수다
 */
function main(){
    bindClickEventOnElementWhichIsActualDate();

    bindClickEventOnElementWhichIsPreOrNextBtn('.pre-month');
    bindClickEventOnElementWhichIsPreOrNextBtn('.next-month');

    bindDynamicalClickEventOnElement('click', '.modify-historys', function (e) {
        let obj = getHistoryInfoWhenClickSubmitBtn(e);

        //call ajax method
        updateHistorys(obj);
    })

    bindDynamicalClickEventOnElement('click', '.delete-history', function(e){
        let obj = getHistoryInfoWhenClickSubmitBtn(e);
        deleteHistorys(obj, e);
    })

    bindClickEventOnElement('.submit-content', () => {

        let history = new History();

        let result = history.checkValidate();


        if(result.result === true) {
            saveHistorys(history);
        }else{
            alert(result.err)
        }
    })
}

main();