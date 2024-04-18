
//방 리스트
const roomList = document.getElementsByClassName("roomList")[0];
//비밀번호 체크박스
const roomPwCheckBox = document.getElementById("roomPwCheckBox");
//비밀번호
const pw = document.getElementById("room_pw");
//방 제목
const roomName = document.getElementsByClassName("roomName")[0];
//방 인원
const memberNum = document.getElementById("memberNum");
//
const pw_group = document.getElementById("password-group");

const btnCloseModal = document.getElementsByClassName('close-modal')[0];
const form = document.getElementsByClassName('modal-content')[0];
function openModal() {
    $(document).ready(function () {
        $('.modal').modal('show');
    })
}
function closeModal(){
    $(document).ready(function () {
        $('.modal').modal('hide');
    })
}

/**
 * .modal-submit 클래스에 click event 부여
 */
$('.modal-submit').click(function(){
    
})

function main(){
    $('.create-room').click( () => openModal())
    $(btnCloseModal).click( () => closeModal() );
    $(form).submit( () => {
        console.log('submit');
        closeModal();
    } );
    $(roomPwCheckBox).click( () => {
        if(roomPwCheckBox.checked){
            $(pw).attr('disabled', 'disabled')
            $(pw).val(' ');
        }else{
            $(pw).removeAttr('disabled')
        }
    } );

}


main();