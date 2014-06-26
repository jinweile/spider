function MzTreeView(Tname)
{
  if(typeof(Tname) != "string" || Tname == "")
    throw(new Error(-1, 'Error!'));
  
  this.url      = "#";
  this.target   = "_self";
  this.name     = Tname;
  this.wordLine = false;
  this.currentNode = null;
  this.useArrow = true;
  this.nodes = {};
  this.node  = {};
  this.names = "";
  this._d    = "\x0f";
  this.index = 0;
  this.divider   = "_";
  this.node["0"] =
  {
    "id": "0",
    "path": "0",
    "isLoad": false,
    "childNodes": [],
    "childAppend": "",
    "sourceIndex": "0"
  };

  this.colors   =
  {
    "highLight" : "#0A246A",
    "highLightText" : "#FFFFFF",
    "mouseOverBgColor" : "#D4D0C8"
  };
  this.icons    = {
    L0        : 'L0.gif',
    L1        : 'L1.gif',
    L2        : 'L2.gif',
    L3        : 'L3.gif',
    L4        : 'L4.gif',
    PM0       : 'P0.gif',
    PM1       : 'P1.gif',
    PM2       : 'P2.gif',
    PM3       : 'P3.gif',
    empty     : 'L5.gif',
    root      : 'root.gif',
    folder    : 'TVRoot.png',
    file      : 'TVModel.png',
    exit      : 'TVRoot.png'
  };
  this.iconsExpand = {
    PM0       : 'M0.gif',
    PM1       : 'M1.gif',
    PM2       : 'M2.gif',
    PM3       : 'M3.gif',
    folder    : 'TVRoot_E.png',

    exit      : 'TVRoot.png'
  };

  this.getElementById = function(id)
  {
    if (typeof(id) != "string" || id == "") return null;
    if (document.getElementById) return document.getElementById(id);
    if (document.all) return document.all(id);
    try {return eval(id);} catch(e){ return null;}
  }

  this.toString = function()
  {
    this.browserCheck();
    this.dataFormat();
    this.setStyle();
    this.load("0");
    var rootCN = this.node["0"].childNodes;
    var str = "<A id='"+ this.name +"_RootLink' href='#' style='DISPLAY: none'></A>";
    
    if(rootCN.length>0)
    {
      this.node["0"].hasChild = true;
      for(var i=0; i<rootCN.length; i++)
        str += this.nodeToHTML(rootCN[i], i==rootCN.length-1);
      setTimeout(this.name +".expand('"+ rootCN[0].id +"', true); "+ 
        this.name +".focusClientNode('"+ rootCN[0].id +"'); "+ this.name +".atRootIsEmpty()",10);
    }

    if (this.useArrow)
    {
      if (document.attachEvent)
          document.attachEvent("onkeydown", this.onkeydown);
      else if (document.addEventListener)
          document.addEventListener('keydown', this.onkeydown, false);
    }
    return "<DIV class='MzTreeView' "+
      "onclick='"+ this.name +".clickHandle(event)' "+
      "ondblclick='"+ this.name +".dblClickHandle(event)' "+
      ">"+ str +"</DIV>";
  };

  this.onkeydown= function(e)
  {
    e = window.event || e; var key = e.keyCode || e.which;
    switch(key)
    {
      case 37 : eval(Tname).upperNode(); break;
      case 38 : eval(Tname).pervNode();  break;
      case 39 : eval(Tname).lowerNode(); break;
      case 40 : eval(Tname).nextNode();  break;
    }
  };
}

MzTreeView.prototype.browserCheck = function()
{
  var ua = window.navigator.userAgent.toLowerCase(), bname;
  if(/msie/i.test(ua))
  {
    this.navigator = /opera/i.test(ua) ? "opera" : "";
    if(!this.navigator) this.navigator = "msie";
  }
  else if(/gecko/i.test(ua))
  {
    var vendor = window.navigator.vendor.toLowerCase();
    if(vendor == "firefox") this.navigator = "firefox";
    else if(vendor == "netscape") this.navigator = "netscape";
    else if(vendor == "") this.navigator = "mozilla";
  }
  else this.navigator = "msie";
  if(window.opera) this.wordLine = false;
};

MzTreeView.prototype.setStyle = function()
{
  var style = "<style>"+
  "DIV.MzTreeView DIV IMG{border: 0px solid #FFFFFF;vertical-align:middle;}" +
  "DIV.MzTreeView DIV SPAN IMG{border: 0px solid #FFFFFF;}";
  if(this.wordLine)
  {
    style +="\
    DIV.MzTreeView DIV\
    {\
      height: 16px;"+
      (this.navigator=="firefox" ? "line-height: 16px;" : "" ) +
      (this.navigator=="netscape" ? "" : "overflow: hidden;" ) +"\
    }\
    DIV.MzTreeView DIV SPAN\
    {\
      vertical-align: middle; font-size: 21px; height: 16px; color: #D4D0C8; cursor: default;\
    }\
    DIV.MzTreeView DIV SPAN.pm\
    {\
      width: "+ (this.navigator=="msie"||this.navigator=="opera" ? "11" : "9") +"px;\
      height: "+ (this.navigator=="netscape"?"9":(this.navigator=="firefox"?"10":"11")) +"px;\
      font-size: 7pt;\
      overflow: hidden;\
      margin-left: -16px;\
      margin-right: 5px;\
      color: #000080; \
      vertical-align: middle;\
      border: 1px solid #D4D0C8;\
      cursor: "+ (this.navigator=="msie" ? "hand" : "pointer") +";\
      padding: 0 2px 0 2px;\
      text-align: center;\
      background-color: #F0F0F0;\
    }";
  }
  style += "<\/style>";
  document.write(style);
};

MzTreeView.prototype.atRootIsEmpty = function()
{
  var RCN = this.node["0"].childNodes;
  for(var i=0; i<RCN.length; i++)
  {
    if(!RCN[i].isLoad) this.expand(RCN[i].id);
    if (RCN[i].text=="")
    {
      var node = RCN[i].childNodes[0], HCN  = node.hasChild;
      if(this.wordLine)
      {
        var span = this.getElementById(this.name +"_tree_"+ node.id);
        span = span.childNodes[0].childNodes[0].childNodes[0];
        span.innerHTML = RCN[i].childNodes.length>1 ? "6" : "5";
      }
      else
      {
        node.iconExpand  =  RCN[i].childNodes.length>1 ? HCN ? "PM0" : "L0" : HCN ? "PM3" : "L3"
        this.getElementById(this.name +"_expand_"+ node.id).src = this.icons[node.iconExpand].src;
      }
    }
  }
};

MzTreeView.prototype.dataFormat = function()
{
  var a = new Array();
  for (var id in this.nodes) a[a.length] = id;
  this.names = a.join(this._d + this._d);
  this.totalNode = a.length; a = null;
};

MzTreeView.prototype.load = function(id)
{
  var node = this.node[id], d = this.divider, _d = this._d;
  var sid = node.sourceIndex.substr(node.sourceIndex.indexOf(d) + d.length);
  var reg = new RegExp("(^|"+_d+")"+ sid +d+"[^"+_d+d +"]+("+_d+"|$)", "g");
  var cns = this.names.match(reg), tcn = this.node[id].childNodes; if (cns){
  reg = new RegExp(_d, "g"); for (var i=0; i<cns.length; i++)
  tcn[tcn.length] = this.nodeInit(cns[i].replace(reg, ""), id); }
  node.isLoad = true;
};

MzTreeView.prototype.nodeInit = function(sourceIndex, parentId)
{
  this.index++;
  var source= this.nodes[sourceIndex], d = this.divider;
  var text  = this.getAttribute(source, "text");
  var hint  = this.getAttribute(source, "hint");
  var sid   = sourceIndex.substr(sourceIndex.indexOf(d) + d.length);
  this.node[this.index] =
  {
    "id"    : this.index,
    "text"  : text,
    "hint"  : hint ? hint : text,
    "icon"  : this.getAttribute(source, "icon"),
    "path"  : this.node[parentId].path + d + this.index,
    "isLoad": false,
    "isExpand": false,
    "parentId": parentId,
    "parentNode": this.node[parentId],
    "sourceIndex" : sourceIndex,
    "childAppend" : ""
  };
     this.nodes[sourceIndex] = "index:"+ this.index +";"+ source;
     this.node[this.index].hasChild = this.names.indexOf(this._d + sid + d)>-1;
  if(this.node[this.index].hasChild)  this.node[this.index].childNodes = [];
  return this.node[this.index];
};

MzTreeView.prototype.getAttribute = function(source, name)
{
  var reg = new RegExp("(^|;|\\s)"+ name +"\\s*:\\s*([^;]*)(\\s|;|$)", "i");
  if (reg.test(source)) return RegExp.$2.replace(/[\x0f]/g, ";"); return "";
};

MzTreeView.prototype.nodeToHTML = function(node, AtEnd)
{
  var source = this.nodes[node.sourceIndex];
  var target = this.getAttribute(source, "target");
  var data = this.getAttribute(source, "data");
  var url  = this.getAttribute(source, "url");
  if(!url) url = this.url;
  if(data) url += (url.indexOf("?")==-1?"?":"&") + data;
  if(!target) target = this.target;

  var id   = node.id;
  var HCN  = node.hasChild, isRoot = node.parentId=="0";
  if(isRoot && node.icon=="") node.icon = "root";
  if(node.icon=="" || typeof(this.icons[node.icon])=="undefined")
    node.icon = HCN ? "folder" : "file";
  node.iconExpand  = AtEnd ? "2" : "4";

  var HTML = "<DIV noWrap='True'><NOBR>";
  if(!isRoot)
  {
    node.childAppend = node.parentNode.childAppend + (AtEnd ? "3" : "1");
    if(this.wordLine)
    {
      HTML += "<SPAN>"+ node.parentNode.childAppend + (AtEnd ? "2" : "4") +"</SPAN>";
      if(HCN) HTML += "<SPAN class='pm' id='"+ this.name +"_expand_"+ id +"'>+</SPAN>";
    }
    else
    {
      node.iconExpand  = HCN ? AtEnd ? "PM2" : "PM1" : AtEnd ? "L2" : "L1";
      HTML += "<SPAN>"+ this.word2image(node.parentNode.childAppend) +"<IMG "+
        "align='absmiddle' id='"+ this.name +"_expand_"+ id +"' "+
        "src='"+ this.icons[node.iconExpand].src +"' style='cursor: "+ (!node.hasChild ? "":
        (this.navigator=="msie"||this.navigator=="opera"? "hand" : "pointer")) +"'></SPAN>";
    }
  }
  HTML += "<A "+
    "href='"+ url +"' "+
    "target='"+ target +"' "+
    "><IMG "+
    "align='absMiddle' "+
    "id='"+ this.name +"_icon_"+ id +"' "+
    "src='"+ this.icons[node.icon].src +"'></A><A "+
    "class='MzTreeview_NodeStyle' hideFocus "+
    "id='"+ this.name +"_link_"+ id +"' "+
    "href='"+ url +"' "+
    "target='"+ target +"' "+
    "title='"+ node.hint +"' "+
    "onmouseover=\"if(this.className=='MzTreeview_NodeStyle'){this.className='MzTreeview_HoverNodeStyle';}\" "+
    "onmouseout=\"if(this.className=='MzTreeview_HoverNodeStyle'){this.className='MzTreeview_NodeStyle';}\" "+
    "onfocus=\""+ this.name +".focusLink('"+ id +"')\" "+
    "onclick=\"return "+ this.name +".nodeClick('"+ id +"')\">"+ node.text +
  "</A></NOBR></DIV>";
  if(isRoot && node.text=="") HTML = "";

  HTML = "\r\n<SPAN id='"+ this.name +"_tree_"+ id +"'>"+ HTML 
  HTML +="<SPAN style='DISPLAY: none'></SPAN></SPAN>";
  return HTML;
};

MzTreeView.prototype.word2image = function(word)
{
  var str = "";
  for(var i=0; i<word.length; i++)
  {
    var img = "";
    switch (word.charAt(i))
    {
      case "1" : img = "L4"; break;
      case "2" : img = "L2"; break;
      case "3" : img = "empty"; break;
      case "4" : img = "L1"; break;
      case "5" : img = "L3"; break;
      case "6" : img = "L0"; break;
    }
    if(img!="")
      str += "<IMG align='absMiddle' src='"+ this.icons[img].src +"' height='16'>";
  }
  return str;
}


MzTreeView.prototype.buildNode = function(id)
{
  if(this.node[id].hasChild)
  {
    var tcn = this.node[id].childNodes, str = "";
    for (var i=0; i<tcn.length; i++)
      str += this.nodeToHTML(tcn[i], i==tcn.length-1);
    var temp = this.getElementById(this.name +"_tree_"+ id).childNodes;
    temp[temp.length-1].innerHTML = str;
  }
};

MzTreeView.prototype.focusClientNode      = function(id)
{
  if(!this.currentNode) this.currentNode=this.node["0"];

  var a = this.getElementById(this.name +"_link_"+ id); if(a){ a.focus();
  var link = this.getElementById(this.name +"_link_"+ this.currentNode.id);
  if(link)link.className="MzTreeview_NodeStyle";
  a.className="MzTreeview_SelectedNodeStyle";
  this.currentNode= this.node[id];}
};

MzTreeView.prototype.focusLink= function(id)
{
  if(this.currentNode && this.currentNode.id==id) return;
  this.focusClientNode(id);
};

MzTreeView.prototype.expand   = function(id, sureExpand)
{
  var node  = this.node[id];
  if (sureExpand && node.isExpand) return;
  if (!node.hasChild) return;
  var area  = this.getElementById(this.name +"_tree_"+ id);
  if (area)   area = area.childNodes[area.childNodes.length-1];
  if (area)
  {
    var icon  = this.icons[node.icon];
    var iconE = this.iconsExpand[node.icon];
    var Bool  = node.isExpand = sureExpand || area.style.display == "none";
    var img   = this.getElementById(this.name +"_icon_"+ id);
    if (img)  img.src = !Bool ? icon.src :typeof(iconE)=="undefined" ? icon.src : iconE.src;
    var exp   = this.icons[node.iconExpand];
    var expE  = this.iconsExpand[node.iconExpand];
    var expand= this.getElementById(this.name +"_expand_"+ id);
    if (expand)
    {
      if(this.wordLine) expand.innerHTML = !Bool ? "+"  : "-";
      else expand.src = !Bool ? exp.src : typeof(expE) =="undefined" ? exp.src  : expE.src;
    }
    if(!Bool && this.currentNode.path.indexOf(node.path)==0 && this.currentNode.id!=id)
    {
      try{this.getElementById(this.name +"_link_"+ id).click();}
      catch(e){this.focusClientNode(id);}
    }
    area.style.display = !Bool ? "none" : "block";
    if(!node.isLoad)
    {
      this.load(id);
      if(node.id=="0") return;

      if(node.hasChild && node.childNodes.length>200)
      {
        setTimeout(this.name +".buildNode('"+ id +"')", 1);
        var temp = this.getElementById(this.name +"_tree_"+ id).childNodes;
        temp[temp.length-1].innerHTML = "<DIV noWrap><NOBR><SPAN>"+ (this.wordLine ?
        node.childAppend +"2" : this.word2image(node.childAppend +"2")) +"</SPAN>"+
        "<IMG border='0' height='16' align='absmiddle' src='"+this.icons["file"].src+"'>"+
        "<A style='background-Color: "+ this.colors.highLight +"; color: "+
        this.colors.highLightText +"; font-size: 9pt'>Waiting...</A></NOBR></DIV>";
      }
      else this.buildNode(id);
    }
  }
};

MzTreeView.prototype.nodeClick = function(id)
{
  var source = this.nodes[this.node[id].sourceIndex];
  eval(this.getAttribute(source, "method"));
  return !(!this.getAttribute(source, "url") && this.url=="#");
};

MzTreeView.prototype.getPath= function(sourceId)
{
  
Array.prototype.indexOf = function(item)
  {
    for(var i=0; i<this.length; i++)
    {
      if(this[i]==item) return i;
    }
    return -1;
  };
  var _d = this._d, d = this.divider;
  var A = new Array(), id=sourceId; A[0] = id;
  while(id!="0" && id!="")
  {
    var str = "(^|"+_d+")([^"+_d+d+"]+"+d+ id +")("+_d+"|$)";
    if (new RegExp(str).test(this.names))
    {
      id = RegExp.$2.substring(0, RegExp.$2.indexOf(d));
      if(A.indexOf(id)>-1) break;
      A[A.length] = id;
    }
    else break;
  }
  return A.reverse();
};

MzTreeView.prototype.focus = function(sourceId, defer)
{
  if (!defer)
  {
    setTimeout(this.name +".focus('"+ sourceId +"', true)", 100);
    return;
  }
  var path = this.getPath(sourceId);
  if(path[0]!="0")
  {
    alert("Node "+ sourceId +" Error! \r\n"+
      "Node id series = "+ path.join(this.divider));
    return;
  }
  var root = this.node["0"], len = path.length;
  for(var i=1; i<len; i++)
  {
    if(root.hasChild)
    {
      var sourceIndex= path[i-1] + this.divider + path[i];
      for (var k=0; k<root.childNodes.length; k++)
      {
        if (root.childNodes[k].sourceIndex == sourceIndex)
        {
          root = root.childNodes[k];
          if(i<len - 1) this.expand(root.id, true);
          else this.focusClientNode(root.id);
          break;
        }
      }
    }
  }
};

MzTreeView.prototype.clickHandle = function(e)
{
  e = window.event || e; e = e.srcElement || e.target;
  switch(e.tagName)
  {
    case "IMG" :
      if(e.id)
      {
        if(e.id.indexOf(this.name +"_icon_")==0)
          this.focusClientNode(e.id.substr(e.id.lastIndexOf("_") + 1));
        else if (e.id.indexOf(this.name +"_expand_")==0)
          this.expand(e.id.substr(e.id.lastIndexOf("_") + 1));
      }
      break;
    case "A" :
      if(e.id) this.focusClientNode(e.id.substr(e.id.lastIndexOf("_") + 1));
      break;
    case "SPAN" :
      if(e.className=="pm")
        this.expand(e.id.substr(e.id.lastIndexOf("_") + 1));
      break;
    default :
      if(this.navigator=="netscape") e = e.parentNode;
      if(e.tagName=="SPAN" && e.className=="pm")
        this.expand(e.id.substr(e.id.lastIndexOf("_") + 1));
      break;
  }
};

MzTreeView.prototype.dblClickHandle = function(e)
{
  e = window.event || e; e = e.srcElement || e.target;
  if((e.tagName=="A" || e.tagName=="IMG")&& e.id)
  {
    var id = e.id.substr(e.id.lastIndexOf("_") + 1);
    if(this.node[id].hasChild) this.expand(id);
  }
};

MzTreeView.prototype.upperNode = function()
{
  if(!this.currentNode) return;
  if(this.currentNode.id=="0" || this.currentNode.parentId=="0") return;
  if (this.currentNode.hasChild && this.currentNode.isExpand)
    this.expand(this.currentNode.id, false);
  else this.focusClientNode(this.currentNode.parentId);
};

MzTreeView.prototype.lowerNode = function()
{
  if (!this.currentNode) this.currentNode = this.node["0"];
  if (this.currentNode.hasChild)
  {
    if (this.currentNode.isExpand)
      this.focusClientNode(this.currentNode.childNodes[0].id);
    else this.expand(this.currentNode.id, true);
  }
}

MzTreeView.prototype.pervNode = function()
{
  if(!this.currentNode) return; var e = this.currentNode;
  if(e.id=="0") return; var a = this.node[e.parentId].childNodes;
  for(var i=0; i<a.length; i++){if(a[i].id==e.id){if(i>0){e=a[i-1];
  while(e.hasChild){this.expand(e.id, true);
  e = e.childNodes[e.childNodes.length - 1];}
  this.focusClientNode(e.id); return;} else {
  this.focusClientNode(e.parentId); return;}}}
};

MzTreeView.prototype.nextNode = function()
{
  var e = this.currentNode; if(!e) e = this.node["0"];
  if (e.hasChild){this.expand(e.id, true);
  this.focusClientNode(e.childNodes[0].id); return;}
  while(typeof(e.parentId)!="undefined"){
  var a = this.node[e.parentId].childNodes;
  for(var i=0; i<a.length; i++){ if(a[i].id==e.id){
  if(i<a.length-1){this.focusClientNode(a[i+1].id); return;}
  else e = this.node[e.parentId];}}}
};

MzTreeView.prototype.expandAll = function()
{
  if(this.totalNode>500) if(
    confirm("Slowly!Continue?")) return;
  if(this.node["0"].childNodes.length==0) return;
  var e = this.node["0"].childNodes[0];
  var isdo = t = false;
  while(e.id != "0")
  {
    var p = this.node[e.parentId].childNodes, pn = p.length;
    if(p[pn-1].id==e.id && (isdo || !e.hasChild)){e=this.node[e.parentId]; isdo = true;}
    else
    {
      if(e.hasChild && !isdo)
      {
        this.expand(e.id, true), t = false;
        for(var i=0; i<e.childNodes.length; i++)
        {
          if(e.childNodes[i].hasChild){e = e.childNodes[i]; t = true; break;}
        }
        if(!t) isdo = true;
      }
      else
      {
        isdo = false;
        for(var i=0; i<pn; i++)
        {
          if(p[i].id==e.id) {e = p[i+1]; break;}
        }
      }
    }
  }
};

MzTreeView.prototype.setIconPath  = function(path)
{
  //var k = 0, d = new Date().getTime();
  //for(var i in this.icons)
  //{
  //  var tmp = this.icons[i];
  //  this.icons[i] = new Image();
  //  this.icons[i].src = path + tmp;
  //  if(k==9 && (new Date().getTime()-d)>50)
  //    this.wordLine = true; k++;
  //}
  for(var i in this.icons)
  {
    var tmp = this.icons[i];
    this.icons[i] = new Image();
    this.icons[i].src = path + tmp;
  }
  for(var i in this.iconsExpand)
  {
    var tmp = this.iconsExpand[i];
    this.iconsExpand[i]=new Image();
    this.iconsExpand[i].src = path + tmp;
  }
};

MzTreeView.prototype.setURL     = function(url){this.url = url;};

MzTreeView.prototype.setTarget  = function(target){this.target = target;};