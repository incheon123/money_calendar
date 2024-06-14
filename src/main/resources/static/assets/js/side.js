import {getSettedDate} from "./calendar.js";


let selected_date = document.getElementsByClassName('setted-date')[0];

export function changeDate(){
    let d = getSettedDate();

    selected_date.innerText = d + "일";
    $(selected_date).attr('data-value', d);
}


