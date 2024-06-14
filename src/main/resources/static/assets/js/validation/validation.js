function isIncludeSpecialCharacter(ele){
    if(/[!$%^&*()_+|~=`{}\[\]:";'<>?,.]/.test(ele)){
       throw new Error(`${ele} include non-valid character`);
    }
    return false;
}

function isDigit(ele){
    if(!(/^[0-9]+$/.test(ele))){
        throw new Error(`${ele} is not a digit`);
    }

    return true;
}
function isObject(ele){
    if(typeof ele !== 'object'){
        throw new Error(`${ele} is not a object`);
    }

    return true;
}

/**
 * check that if obj is object
 * @param obj
 */
function isInputChecked(obj){
    isObject(obj);

    return true;
}

/**
 * check that given ele is string
 * @param ele
 * @returns {boolean}
 */
function isString(ele){
    if(typeof ele !== 'string'){
        throw new Error(`${ele} is not a string`);
    }

    return true;
}
/**
 *
 * @param ele
 * @returns {boolean}
 */
function isNumber(ele){
    if(typeof ele !== 'number'){
        throw new Error(`${ele} is not a number`);
    }

    return true;
}
/**
 * ele 길이가 주어진 길이보다 긴지 체크합니다
 * 길다면 true, 길지 않다면 false를 반환합니다.
 * @param ele
 * @param len
 */
function isEleLongerThanGivenLen(ele, len){
    isString(ele);

    return true;
}
/**
 * ele의 값이 비어있는지 체크한다
 * 비어있으면 true 아니면 false
 * @param ele
 * @returns {boolean}
 */
function isEmpty(ele){
    return ele.value.length <= 0;
}
/**
 * r1과 r2 범위에 ele 요소가 포함되는지 확인한다
 * @param ele
 * @param r1
 * @param r2
 * @returns {boolean}
 */
function isBetween(ele, r1, r2){
    return ele >= r1 && ele <= r2;
}

let validation = {

}