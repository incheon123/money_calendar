import {setLineChartConfig, setLineChartData} from "./chart/lineChart.js";
import {setBarChartConfig, setBarChartData} from "./chart/barChart.js";

const ctx = document.getElementById('myChart');
const ctx2 = document.getElementById('myChart2');

function jsonToArr(json){
    let arr = [];

    for(let i = 1; i <= Object.keys(json).length; i++){
        arr.push(json[i])
    }

    return arr;
}

//특정 달에 대한 통계
$.ajax({
    url: '/money_management/get/monthTotalIncomeAndOutcome',
    cache: false,
    data: JSON.stringify({
        //현재 날짜를 가져오는 함수를 호출해야됨
        year: '2024',
        month: '3'
    }),
    dataType: 'json',
    contentType: 'application/json',
    type: 'post',
    success: function (e) {
        let d = setBarChartData(['3월'], e[0], e[1])
        new Chart(ctx, setBarChartConfig(d, 'bar'));
    },
    error: function(e){
        console.log(e);
    }
})

//특정 년도에 대한 월간 통계
$.ajax({
    url: '/money_management/get/yearTotalIncomeAndOutcome',
    cache: false,
    data: JSON.stringify({
        year: '2024'
    }),
    dataType: 'json',
    contentType: 'application/json',
    type: 'post',
    success: function (e) {
        let in_arr = jsonToArr(e['income'])
        let out_arr = jsonToArr(e['outcome'])
        let d = setLineChartData([1,2,3,4,5,6,7,8,9,10,11,12], in_arr, out_arr)
        new Chart(ctx2, setLineChartConfig(d));
    },
    error: function(e){
    }
})