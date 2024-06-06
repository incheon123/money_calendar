import { bindClickEventOnElement } from "./calendar.js";
import {setCalendarTitleDate} from "./date.js";
import { emptyDatesHistoryList, initCalendar } from "./getHistorys.js";


/**
 * 수정하기 버튼, 삭제하기을 누르면 실행된다
 */
export function getHistoryInfoWhenClickSubmitBtn(e){
    let history_no = e.currentTarget.value;
    let ele = $(`.input-${history_no}`);

    let obj = {
        year: ele[1].value,
        month: ele[2].value,
        date: ele[3].value,
        content_no: ele[0].value,
        money: ele[6].value,
        content: ele[7].value,
    }
    if (ele[4].checked) {
        obj['type'] = ele[4].value;
    } else {
        obj['type'] = ele[5].value;
    }

    return obj;
}


let container = document.getElementsByClassName("historys_container")[0];
var template;
export function createHistoryForm(idx, y,m,d,content_no, money, content, type){

    template = document.createElement("template");

    let incomeInput = `<input type="radio" name="type${idx}" value="income" class="radio input-${idx}" required`;
    let outcomeInput = `<input type="radio" name="type${idx}" value="outcome" class="radio input-${idx}" required`;

    if(type === 'income') {
        incomeInput += ` checked>`
        outcomeInput += ` >`
    }
    else {
        incomeInput += ` >`
        outcomeInput += ` checked>`
    }

    template.innerHTML = `
        <div class="w-100 h-100 history-input-form bg-white">
            <input type="hidden" value="${content_no}" class="input-${idx}">
            <input type="hidden" value="${y}" class="input-${idx}">
            <input type="hidden" value="${m}" class="input-${idx}">
            <input type="hidden" value="${d}" class="input-${idx}">
            <div class="d-flex justify-content-around">
                <div>
                    <span>수입</span>
                    ${incomeInput}
                </div>
                <div>
                    <span>지출</span>
                    ${outcomeInput}
                </div>
            </div>
            <div class="w-100">
                <p>얼마</p>
                <input type="text" class="w-100 mx-0 px-0 input-${idx}" name="money" required value="${money}">
            </div>
            <div class="w-100 h-75">
                <p>무엇을</p>
                <textarea name="content" class="h-75 input-${idx}" required>${content}</textarea>
            </div>
            <button type="button" class="btn btn-primary submit-content modify-historys" value="${idx}">작성하기</button>
            <button type="button" class="btn btn-danger submit-content delete-history" value="${idx}">삭제하기</button>
        </div>
    `;

    var fragment = template.content;
    container.appendChild(fragment);
}

export function emptyActualDate(){
    $('.money').remove();
}


export function empty(ele){
    ele.empty();
    ele.innerHTML = " ";
}

export function updateHistoryContainer(e) {
    console.log($(e.currentTarget).parent().get()[0].remove());
}

export function getSelectedDate(myHistory){
    console.log(myHistory)
    empty($(container));

    for(let i = 0 ; i < myHistory.length; i++){

        createHistoryForm(
            i,
            myHistory[i].year,
            myHistory[i].month,
            myHistory[i].date,
            myHistory[i].content_no,
            myHistory[i].money,
            myHistory[i].content,
            myHistory[i].type
        )
    }
}

function bindClickEvent(btns, type){
    emptyDatesHistoryList();
    initCalendar(type);
    btns.forEach(function (btn){

        bindClickEventOnElement(btn, function(e){    
            //달력의 날짜를 설정
            setCalendarTitleDate(btn);
            console.log('btn is -> ' + btn);
            //초기화
            initCalendar(type);
        })

    })
}

export function clickPrevNextBtn(...ele){
    bindClickEvent(ele, "PRIVATE")
}
export function clickPrevNextBtnChatting(...ele){
    bindClickEvent(ele, "CHATTING")
}