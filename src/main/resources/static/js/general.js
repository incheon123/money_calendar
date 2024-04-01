export class History{
    type = $('.input-radio:checked')
    money = $('input[name="input-money"]');
    content = $('textarea[name="input-content"]');
    year = $('.year').attr('data-year');
    month = $('.month').attr('data-month') ;
    date = $('.setted-date').attr('data-value');

    toString = () => {
        console.log(`history = [
                ${this.type.val()} +
                ${this.money.val()} +
                ${this.content.val()} +
                ${this.year} +
                ${this.month} +
                ${this.date} +
            }]`)
    }
    get = () => {
        return {
            year: this.year,
            month: this.month,
            date: this.date,
            type: this.type.val(),
            money: this.money.val(),
            content: this.content.val()
        }
    };
    checkValidate = () => {
        if(this.type.val() === '') return {"result": false, 'err': "type undefined"};
        if(this.money.val() === '') return {"result": false, 'err': "money undefined"};
        else{
            if(!/\d/.test(this.money.val())) return {"result": false, "err": "money is not a number"}
        }
        if(this.content.val() === '') return {"result": false, 'err': "content undefined"};
        if(this.year === '') return {"result": false, 'err': "year undefined"};
        if(this.month === '') return {"result": false, 'err': "month undefined"};
        if(this.date === '') return {"result": false, 'err': "date undefined"};

        return {"result": true, "err": "none"};
    }
    clear = () => {
        console.log($('.input-radio'));
        $(this.money).val('');
        $(this.content).val('');
    }

}

$('.logout').on('click', function(){
    // $.ajax({
    //     url: '/money_management/logout',
    //     cache: false,
    //     type: 'post',
    //     success: function(){
    //         alert("안녕히 가십시오");
    //     }
    // })
})