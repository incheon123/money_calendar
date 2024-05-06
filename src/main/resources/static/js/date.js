import { getCurrentDate } from "./calendar.js";
import { setDate } from "./calendar.js";
import { makeCalendar } from "./calendar.js";
import { main } from "./calendar.js"

var target_year     = document.querySelector(".year");
var target_month    = document.querySelector('.month');

var dates = document.getElementsByClassName("date-box");
var days      = document.getElementsByClassName("day");

export function setCurrentDate(){
    let date_arr = getCurrentDate();
    $(target_year).text(date_arr[0] + "년 ");
    $(target_year).attr('data-year', date_arr[0]);

    $(target_month).text(date_arr[1] + "월 ")
    $(target_month).attr('data-month', date_arr[1]);

}

export function setCurrentDay(){

    let day = getCurrentDate()[3];
    let attr = $(days[day]).attr('class')
    attr += ' active'

    days[day].setAttribute('class', attr)
}

export function removeCurrentDay(){
    //요일을 세팅하기 전 색칠한다
    for(let i = 0; i < days.length; i++){
        let cls = days[i].className.slice(0, 7);
        days[i].className = cls;
    }
}

let d;
let year;
let month;
let date;

function setttingDate(){
    d = getCurrentDate();

    year = d[0];
    month = d[1];
    date = d[2];
}

export function setCalendarTitleDate(ele){

    if(ele === '.pre-month')
        setDateToPreMonth();
    else
        setDateToNextMonth();
    
}

export function setDateToPreMonth(){

    setttingDate();

    if(1 >= month){
        year -= 1
        month = 12
    }else{
        month -= 1
    }

    //달력을 바꿔줌
    setDate(new Date(year, month-1, date));
    main();

}

export function setDateToNextMonth(){
    setttingDate();

    if(12 <= month){
        year += 1
        month = 1
    }else{
        month += 1
    }

    //달력을 바꿔줌
    setDate(new Date(year, month-1, date));
    main();
}




export function getCurrentSettingDate(){
    let d = new Date();
    return d.getFullYear() + d.getMonth()+1;
}


