import { setCurrentDate } from "./date.js";
import { setCurrentDay } from "./date.js";
import { getCurrentSettingDate } from "./date.js";
import { removeCurrentDay } from "./date.js";
import {changeDate} from "./side.js";

//달력을 만들 엘리먼트
var calendar         = document.querySelector(".calendar");

//calendar 엘리먼트의 너비와 하이트
const CAL_WIDTH = window.getComputedStyle(calendar).width;
const CAL_HEIGHT = window.getComputedStyle(calendar).height;

const ROW = 7
const COLUMN = 6

//켈린더 엘리먼트에 들어갈 div
const DATE_BLOCK_WIDTH = Number(CAL_WIDTH.replace('px', ''));
const DATE_BLOCK_HEIGHT = Number(CAL_HEIGHT.replace('px', ''));

//달력 상단에 있는 세 개의 금액, 제한금액 소득금액 지출금액
const LIMIT_MONEY = document.getElementsByClassName("limit-money")[0];
const INCOME_MONEY = document.getElementsByClassName("income-money")[0]
const OUTCOME_MONEY = document.getElementsByClassName("outcome-money")[0];

//날짜
let date = new Date();


export function emptyMoney(){
    LIMIT_MONEY.innerText = "미정"
    INCOME_MONEY.innerText = "미정"
    OUTCOME_MONEY.innerText = "미정"
}


export function getCurrentDate(){
    return [
        Number(date.getFullYear()),
        Number((date.getMonth()+1)),
        Number(date.getDate()),
        Number(date.getDay())
    ];
}
function getMonth(){
    return Number((date.getMonth()+1));
}
export function setDate(d){
    date = d;
}

//블럭을 만든다
function createDateBlock(){
    for(let i = 0; i < ROW * COLUMN; i++){

        let div = document.createElement("div");

        //calendar의 길이를 7로 나누면 그것이 div의 너비
        div.style.width = ( DATE_BLOCK_WIDTH / ROW ) + 'px';
        div.style.height = ( DATE_BLOCK_HEIGHT / COLUMN ) + 'px';
        div.style.border = "1px solid grey";
        div.style.borderCollapse = "collapse"
        div.setAttribute('class', 'date-box')


        calendar.append(div);
    }
}

function getMonthLastDate(){
    //달의 길이, 달에 +1을 해줘야 원하는 달의 일수가 나온다.
    //date에 0을 넘기면 저번달로 바뀌기 때문에 달에 +1만큼 해준다.
    return new Date(date.getFullYear(), date.getMonth()+1, 0).getDate();
}

function getMonthFirstDay(){
    return new Date(date.getFullYear(), date.getMonth(), 1).getDay();
}

export function bindClickEventOnElement(ele, event){
    $(ele).on('click', event)
}

let settedDate;
export function getSettedDate(){
    return settedDate;
}

function insertDate(){
    let dateBox = document.getElementsByClassName("date-box");
    let f_day = getMonthFirstDay()
    let arr = getCurrentDate()
    let today = arr[0] + arr[1];

    let target_date;
    for(let i = 1; i <= getMonthLastDate(); i++){
        let p = document.createElement("h5")
        p.innerText = i;

        //-------------------


        dateBox[f_day].append(p);
        dateBox[f_day].style.backgroundColor = "rgb(142, 189, 186)"
        $(dateBox[f_day]).attr('class', $(dateBox[f_day]).attr('class') + ' actualDate ' + `d_${i}`)
        $(dateBox[f_day]).attr('value', i)

        if(today == getCurrentSettingDate()){
            if(date.getDate() == i){
                target_date = dateBox[f_day]
            }
        }

        f_day++;
    }

    //주말에는 텍스트 칼라 색상을 빨강색으로 변경한다.

    let actualDate = document.getElementsByClassName("actualDate");
    for(let i = 0 ; i < actualDate.length; i++){
        let date = actualDate[i].attributes.value.value;
        let day = new Date(getCurrentDate()[0], getCurrentDate()[1] - 1, Number(date)).getDay();

        if(day == 0 || day == 6){
            actualDate[i].children[0].style.color = "red";
        }
    }

    if(today === getCurrentSettingDate()) setCurrentDay();
    else                                 removeCurrentDay();

    if(target_date == null){
        target_date = dateBox[getMonthFirstDay()];
    }

    target_date.style.border = "1px solid hotpink"

    bindClickEventOnElement('.actualDate', function(e){
        settedDate = e.currentTarget.attributes.value.value;
        console.log(getMonth())
        changeDate();

        //타켓 데이트 변경
        target_date.style.border = '1px solid grey'

        target_date = e.currentTarget;
        target_date.style.border = '1px solid hotpink';

    });
}

export function deleteCalendar(){

    //달력 본체
    calendar.innerHTML = "";
}

//시작점
export function makeCalendar(){
    deleteCalendar();
    createDateBlock();

    insertDate();
}

function makeDate(){
    setCurrentDate();
}
export function main(){
    makeCalendar();
    makeDate();
}

main();