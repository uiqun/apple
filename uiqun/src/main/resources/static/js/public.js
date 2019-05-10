/**
 * Created by David on 2019-01-10.
 */

function addPn(){
    window.open("addPn.html","_self");
}

function vendor(){
    window.open("vendor.html","_self");
}

function platInfo(){
    window.open("platInfo.html","_self");
}

function company(){
    window.open("company.html","_self");
}

function userInfo(){
    window.open("userInfo.html","_self");
}

function findPrice(){
    window.open("findPrice.html","_self");
}

function hotStk(){
    window.open("hotStk.html","_self");
}

function index(){
    window.open("index.html","_self");
}

function logon(){
    window.open("logon.html","_self");
}

function quote(){
    window.open("quote.html","_self");
}

function regist(){
    window.open("regist.html","_self");
}

function rfq(){
    window.open("rfq.html","_self");
}

function rfqDetail(){
    window.open("rfqDetail.html","_self");
}

function user(){
    window.open("user.html","_self");
}

function BOM(){
    window.open("BOM.html","_self");
}

function admin(){
    window.open("admin.html","_self");
}

function addPn(){
    window.open("addPn.html","_self");
}

function addMfg() {
    window.open("addMfg.html", "_self");
}


function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}