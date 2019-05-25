/**
 * Created by David on 2019-01-10.
 */

function addPn(){
    window.open("/addpn","_self");
}

function vendor(){
    window.open("/queryUtypes","_self");
}

function platInfo(){
    window.open("platInfo.html","_self");
}

function company(){
    window.open("company.html","_self");
}

function userInfo(){
    window.open("/queryUserDetail","_self");
}

function findPrice(){
    window.open("/queryBomlists","_self");
}

function hotStk(){
    window.open("hotStk.html","_self");
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
    window.open("regist.html","_self");
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
    var AlertMessage = document.getElementById("AlertMessage").value;
    if(AlertMessage!=null&&AlertMessage!=''){
        alert(AlertMessage);
    }
})