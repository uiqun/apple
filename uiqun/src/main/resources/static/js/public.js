/**
 * Created by David on 2019-01-10.
 */

function addPn(){
    window.open("/addpn","_self");
}

function vendor(){
    window.open("/queryLevel/11","_self");
}

function platInfo(){
    window.open("/queryPlatinfo","_self");
}

function company(){
    window.open("company.html","_self");
}

function userInfo(){
    window.open("/queryUserDetail","_self");
}

function findPrice(){
    window.open("/findPrice","_self");
}

function hotStk(){
    window.open("/queryHotstks","_self");
}

function index(){
    window.open("/index","_self");
}

function logon(){
    window.open("/user/login","_self");
}

function quote(){
    window.open("/inquote1","_self");
}

function regist(){
    window.open("/user/regist","_self");
}

function forgetPw(){
    window.open("/user/forgetPw","_self");
}
function rfq(){
    window.open("/jumprfq","_self");
}


function user(){
    window.open("user.html","_self");
}

function BOM(){
    window.open("/bom/searchbom","_self");
}

function admin(){
    window.open("/user/admin","_self");
}

function addPn(){
    window.open("/addpn","_self");
}

function addMfg() {
    window.open("/addmfg", "_self");
}


function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}
$(function () {
    var AlertMessage = document.getElementById("AlertMessage");
    if(AlertMessage!=null) {
        var AlertMessageValue = AlertMessage.value;
        if (AlertMessageValue != null && AlertMessageValue != '') {
            alert(AlertMessageValue);
        }
    }
})