/**
 * 현재 방에 참여한(온라인 유저) 유저를 저장하는 리스트
 */
let userList = new Array();



class user
{
    #id;
    #email;
    #gender;
    #role;
    #isMe;

    constructor(id, email, gender, role, isMe)
    {
        this.#id = id;
        this.#email = email;
        this.#gender = gender;
        this.#role = role;
        this.#isMe = isMe;
    }

    get id(){
        return this.#id;
    }
    get email(){
        return this.#email;
    }
    get gender(){
        return this.#gender;
    }
    get role(){
        return this.#role;
    }
    get isMe(){
        return this.#isMe;
    }
}