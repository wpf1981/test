import{j as v,m as i,g as y,n as B,p as u,a as k,e as p,d as o,w as l,u as x,F as D,r as n,o as C,f as E}from"./index.1bde25f7.js";import{h as N}from"./moment.17f8281e.js";const Y=E(" \u70B9\u51FB\u4E0A\u4F20 "),I=v({setup(z){const f=(a,t)=>{console.log("----------------"),console.log(a,t.fileUrl);const e=document.createElement("a");e.style.display="none",e.href=t.fileUrl,e.setAttribute("download",t.fileName),document.body.appendChild(e),e.click()},m=(a,t)=>{const e=a[t.property];return e===void 0?"":N(e).format("YYYY-MM-DD")},s=new FormData,_=(a,t)=>{t.length>0&&(s.append("File",a.raw),u.post("/upfile",s).then(e=>{console.log(e.data),s.delete("File")}).catch(e=>{console.log(e)}))},h=i(),d=y({arr:[]}),F=i([]);return B(()=>{u.get("/getfile").then(a=>{const t=a.data;h.value=t.code,d.arr=t.data,F.value=t.data})}),(a,t)=>{const e=n("el-button"),b=n("el-upload"),r=n("el-table-column"),g=n("vue-feather"),w=n("el-table");return C(),k(D,null,[p("div",null,[o(b,{action:"",multiple:"",limit:10,accept:".pdf","on-change":_},{default:l(()=>[o(e,{size:"small",type:"primary"},{default:l(()=>[Y]),_:1})]),_:1})]),p("div",null,[o(w,{height:"80vh",data:x(d).arr,style:{width:"100%"},border:"",stripe:""},{default:l(()=>[o(r,{fixed:"",prop:"fileId",label:"ID",width:"50"}),o(r,{prop:"fileName",label:"\u540D\u79F0",width:"200"}),o(r,{prop:"ct",label:"\u65E5\u671F",width:"150",formatter:m}),o(r,{fixed:"right",label:"\u70B9\u51FB\u4E0B\u8F7D",width:"auto"},{default:l(c=>[o(e,{size:"small",type:"text"},{default:l(()=>[o(g,{type:"download","stroke-width":"1.5",size:"24",onClick:M=>f(c.$index,c.row)},null,8,["onClick"])]),_:2},1024)]),_:1})]),_:1},8,["data"])])],64)}}});export{I as default};