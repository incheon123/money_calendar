import { clickPrevNextBtn, clickPrevNextBtnChatting } from "./module.js";

function clickForPrevCalendar(){
    clickPrevNextBtnChatting('.pre-month')
}

function clickForNextCalendar(){
    clickPrevNextBtnChatting('.next-month')
}

export function executeCalendarBtn(){
    console.log("room + executeCalendarBtn")
    clickForNextCalendar();
    clickForPrevCalendar();
}

executeCalendarBtn();