//?????????????
function checkNumber(obj)
{
	var objValue = obj.value;
    if(isNaN(objValue)==0)
        return true;
	else
		return false;
}

//???? ?? ? lili@si-tech.com.cn
function checkMail(obj)
{
	if(obj.value.indexOf('@') == -1 ||obj.value.indexOf('.') == -1 || obj.value.length < 6)
	    return false;
	else 
	    return true;
}

//???
function checkEmpty(OBJ)
{
	var value = OBJ.value;
	if (value == "" || value == "undefine" || value == "null") 
	    return true;
	else 
	    return false;
}

function checkStrLength(obj_input,strlen)
{
	var str = obj_input.value;
	if(str.length>strlen){
		alert("长度不能大于"+strlen+"!");
		obj_input.value = str.substr(0,str.length-1);
	}
}

function popwindow(htmlurl,state)
{window.open(htmlurl,"","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no"+state);}

function popWindowScroll(htmlurl,state)
{window.open(htmlurl,"","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes"+state);}

function popWindowCenter(htmlurl,wid,heig)
{
	scWidth=screen.Width/2-wid/2;
	scHeight=screen.Height/2-heig/2;
	window.open(htmlurl,"","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,"+",left="+scWidth+",top="+scHeight+",width="+wid+",height="+heig);	
}

function popWindowCenterNormal(htmlurl,wid,heig)
{
	scWidth=screen.Width/2-wid/2;
	scHeight=screen.Height/2-heig/2;
	window.open(htmlurl,"","toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,"+",left="+scWidth+",top="+scHeight+",width="+wid+",height="+heig);	
}

function SetCwinHeight(idstr)
{
	var cwin=document.getElementById(idstr);
	if (document.getElementById)
	{
		if (cwin && !window.opera)
		{
			if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)
			cwin.height = cwin.contentDocument.body.offsetHeight; 
			else if(cwin.Document && cwin.Document.body.scrollHeight)
			cwin.height = cwin.Document.body.scrollHeight;
		}
	}
}

//<!--   -->
//<!--   分割字符串 string  得到 key的值   -->
//<!--   例如 ：string ＝ asdf.do?group=33443&df=4    -->
//<!--         key   ＝ group   -->
//<!--         返回 33443   -->


function spit(string, key) {
     var index = string.indexOf("?");
	 var value = "";
	 var result = "";
	 if (index == -1) {
	     return  "-1";
	 } else {
	     value = string.substr(index + 1, string.length);	     
	 }
	 var aryReturn = getAryData(value, "&");
	 for (var i=0; i < aryReturn.length; i++) {
	     value = aryReturn[i];
         index = value.indexOf("=");			
         if (value.substr(0, index) == key) {		   
		     result = value.substr(index + 1, value.length);
			 break;
		 }		
	 }
	 return result;
 }



//<!--   把一个以 dd;dsds;bb;sds; 为title 的 对象的title分开后-oSrc 加入 textarea 里－>oDest   -->

 function spitAndInsert(oSrc, oDest) {
	 var aryReturn = getAryData(oSrc.value, ';')
	 for (var i=0; i < aryReturn.length; i++){
		oDest.value = oDest.value + aryReturn[i] + '\n';
	 }
 }

//<!--  -->
//<!--   分割字符串 string   --> 
//<!--   按 key 分割成数组   -->

 function getAryData(string , key) {
	 var aryReturn = new Array();
	 var aryReturn = string.split(key);
	 return aryReturn;
 }



function moveSelected(oSrc, oDest){
  for (x in oSrc.childNodes){
    var node = oSrc.childNodes[x];
    if(!node.selected){
      continue;
    }

    addUniqueNode(node, oDest);
  }
}

function insert(oDest,name, value){
	var oNewNode = document.createElement("option");
	oNewNode.innerText=name;
   	oNewNode.value = value;
	addUniqueNode(oNewNode, oDest)
	

}
function  addUniqueNode(node, oDest){
	var oNewNode = document.createElement("option");
    var nodeExist = false;
    for(y in oDest.children){
      if(node.value == oDest.children[y].value){
        nodeExist = true;
        break;
      }
    }

    if(!nodeExist){
      var newNode = node.cloneNode(true);
      oDest.appendChild(newNode);
    }
}
function removeSelected(oSelect){
  for( i=oSelect.childNodes.length -1;i>=0;i--){
    var node = oSelect.childNodes(i);
    if(node.selected){
      oSelect.removeChild(node);
    }
  }
}
function removeAll(oSelect){
  for( i=oSelect.childNodes.length -1;i>=0;i--){
    oNode = oSelect.childNodes(i);
    oSelect.removeChild(oNode);
  }
}
function selectOrg( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("course_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+";";
          oValue.value=oValue.value+ret[x].id+";";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
          
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}
function selectOrgCC( oValue, oView){
  var num = Math.random();
  var idAndName = new Array(oValue.value , spitToString(oView));
  var ret = window.showModalDialog("../../../page/system/courseallot/compulsory_course_allot.jsp",idAndName,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
          
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}
function selectOrgM( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("major_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}

function selectOrgC( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("majorcourse_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
           // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}

function selectOrgR( oValue, oView){
  var num = Math.random();
  var idAndName = new Array(oValue.value , spitToString(oView));
  
  var ret = window.showModalDialog("../../../page/system/roleallot/role_allot.jsp",idAndName,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}

function selectOrgZ( oValue, oView){
  var num = Math.random();
  var idAndName = new Array(oValue.value , spitToString(oView));
  
  var ret = window.showModalDialog("../../../page/university/courseforclazz/course_allot.jsp",idAndName,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}

function selectOrgE( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("examination_paper_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}

function trimString(objArray)
{
	var size = objArray.length;
	
	for (var i = 0; i < size; i++ ){
	    objArray[i] = objArray[i].replace(/\s/g,"");
	}
}

function spitToString(objTextArea){
    if (objTextArea) {
        var strHTML = objTextArea.value;
	    var aryReturn = strHTML.split("\n");
	    trimString(aryReturn);
	    var result="";
	    for(var i = 0; i < aryReturn.length - 1; i++) {
	        result = result + aryReturn[i] + ",";
	       // alert(result);
	    }
	    result = result + aryReturn[aryReturn.length - 1];
	    return result;
    } else{
        return "";
    }
}
function selectOrgA( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("examination_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
           // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}


function selectOrgU( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("user_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
          // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}


function selectOrgUG( oValue, oView){
  var num = Math.random();
  var ret = window.showModalDialog("userGroup_allot.jsp",oValue.value,"dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
  var i = 0;
  if( ret != null){
      oValue.value="";
      oView.value="";
	  oView.title="";
      for( x in ret){
          i++;
		  oView.title=oView.title + ret[x].name+",";
          oValue.value=oValue.value+ret[x].id+",";
         // if(i<3){
             oView.value = oView.value+ret[x].name+"\n";
         // }else if(i==3){
         //    oView.value = oView.value+"...";
         // }else{
             
         // }
      }
      if(i==0){
         oValue.value="";
         oView.value="";
      }
  }
}


function Element(id, name, type){
  this.id=id;
  this.name=name;
  this.type=type;
}
function buildReturnValue(oSelect){
  var ret = new Array();
  for (x in oSelect.children){
    if(oSelect.children[x].value==null){
      continue;
    }
    var o = new Element(oSelect.children[x].value, oSelect.children[x].innerText, 0);
    ret.push(o);
  }

  return ret;
}
function buildValue(oSelect){
  var ret = "";
  for (x in oSelect.children){
   
    if(oSelect.children[x].value == null){
      continue;
    }   
    var o = oSelect.children[x].value;
    ret = ret + o + ",";
  }
 
  return ret;
}

function showselect(url)
{	
	window.showModalDialog(url,"55555","dialogHeight: 400px; dialogWidth: 710px; dialogTop: px; dialogLeft: px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");			
}