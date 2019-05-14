<%--
  Created by IntelliJ IDEA.
  User: {xieyiyang}
  Date: 2019/5/5
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base
            href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+"/"%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>SnCheck后台</title>
    <!-- jquery bootstrap js -->
    <script src="js/jquery-1.12.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"
            charset="utf-8"></script>
    <script type="text/javascript" src="js/JsBarcode.all.js"></script>

    <!--Bootstrap CSS Link-->
    <link rel="stylesheet" type="text/css"
          href="bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="bootstrap/css/bootstrap-theme.css"/>
    <!-- Layui Link-->
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.all.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Sn-扫码</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">Admin
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a  href="/page/import">Sn数据导入</a>
                </li>
                <li class="layui-nav-item ">
                    <a  href="/page/toScan">Sn扫码</a>
                </li>
                <li class="layui-nav-item layui-this ">
                    <a  href="/page/toWork">SN工作台</a>
                </li>
            </ul>
        </div>
    </div>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="SnTable" style="text-align: center">
            箱号：
            <div class="layui-inline">
                <input class="layui-input" name="boxCode" id="boxCode" autocomplete="off">
            </div>
            Sn序列号：
            <div class="layui-inline">
                <input class="layui-input" name="snCode" id="snCode" onautocomplete="off">
            </div><br>
            <div class="layui-inline" id="message">
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-md9">
                <table class="layui-table" id="snT" lay-filter="">
                    <thead>
                    <th>物料编码</th>
                    <th>任务</th>
                    <th>备料号</th>
                    <th>箱号</th>
                    <th>SN</th>
                    <th>属性号</th>
                    </thead>
                    <tbody>

                    </tbody>

                </table>
            </div>
            <div class="layui-col-md3">
                <!--startprint-->
                <div class="layui-show" id="snBox">

                </div>
                <!--endprint-->
                <button class="layui-btn" onclick="myprint()">打印</button>
            </div>
        </div>
    </div>
</div>

<div class="layui-footer">
    <!-- 底部固定区域 -->
    © Ruijie
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });



    $("#snCode").blur(function(){
        $("#message").empty();
        check();
    });


    function myprint(){

        //直接调用浏览器打印功能
        bdhtml=window.document.body.innerHTML;
        //定义打印区域起始字符，根据这个截取网页局部内容
        sprnstr="<!--startprint-->"; //打印区域开始的标记
        eprnstr="<!--endprint-->";   //打印区域结束的标记
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
        window.document.body.innerHTML=prnhtml;
        //开始打印
        // document.getElementById("WebBrowser").ExecWB(6,6);
        // window.document.all.WebBrowser.ExecWB(6,6)
        window.print();
        //还原网页内容
        window.document.body.innerHTML=bdhtml;
    }
    function tiaoma(s,sncode) {
        var barcode = document.getElementById(sncode);
            options = {
                format:"CODE128",
                displayValue:true,
                fontSize:18,
                height:100
            };
        JsBarcode(barcode, s, options);//原生
        $(sncode).JsBarcode(s,options);//jQuery
    }
    function check() {
        var boxCode=$("#boxCode");
        var snCode=$("#snCode");

        $.ajax({
            type:"POST",
            url:"/meterial/check",
            async:true,
            data:{
                "boxCode":boxCode.val(),
                "snCode":snCode.val()
            },

            success:function (res) {
                if (res.status==200){
                    layer.closeAll('loading');
                    $("#snT tbody").empty();
                    var data=res.body;
                    //物料编号
                    var materielCode = $("<td></td>").append(data.materielCode);
                    //任务
                    var task = $("<td></td>").append(data.task);
                    //箱号
                    var boxCode = $("<td></td>").append(data.boxCode);
                    //sn编码
                    var snCode = $("<td></td>").append(data.snCode);
                    //备料号
                    var spareCode = $("<td></td>").append(data.spareCode);
                    //属性号
                    var attributeCode = $("<td></td>").append(data.attributeCode);

                    $("<tr></tr>").append(materielCode).append(task).append(boxCode).append(snCode).append(spareCode).append(attributeCode)
                        .append(attributeCode).appendTo("#snT tbody");
                    $("<img id='"+data.snCode+"' /><br >").appendTo("#snBox")
                    tiaoma(data.attributeCode,data.snCode);
                    $("#snCode").val("").focus();
                }
                if(res.status==1){
                    layer.confirm(res.message, function(index){
                        $("#boxCode").val("").focus();
                        $("#snCode").val("");
                        layer.close(index);
                    });

                }
            }

        });
        layui.use('table', function() {
            var table = layui.table;
            //监听行工具事件
            table.on('tool(test)', function(obj){
                var data = obj.data;
                //console.log(obj)
                if(obj.event === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        obj.del();
                        layer.close(index);
                    });
                } else if(obj.event === 'edit'){
                    layer.prompt({
                        formType: 2
                        ,value: data.email
                    }, function(value, index){
                        obj.update({
                            email: value
                        });
                        layer.close(index);
                    });
                }
            });
        });

    }


</script>

</body>
</html>
