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
                <li class="layui-nav-item layui-this">
                    <a  href="/page/toScan">Sn扫码</a>
                </li>
            </ul>
        </div>
    </div>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="SnTable" style="text-align: center">
            箱号：
            <div class="layui-inline">
                <input class="layui-input" name="task" id="task" autocomplete="off">
            </div>
            Sn序列号：
            <div class="layui-inline">
                <input class="layui-input" name="snCode" id="snCode" autocomplete="off">
            </div>
            <button class="layui-btn" onclick="toScan()">搜索</button>
        </div>
        <table class="layui-table" id="snT" lay-filter="">
             <thead>
              <th></th>
              <th>ID</th>
              <th>物料编码</th>
              <th>箱号</th>
              <th>属性号</th>
              <th>打印人</th>
              <th>打印时间</th>
              <th>打印次数</th>
              <th>最后一次打印人</th>
              <th>最以后一次打印时间</th>
              <th>最后一次打印次数</th>
              <th>创建时间</th>
              <th>操作</th>
              </thead>
            <tbody>

            </tbody>

        </table>
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
    function toScan() {
        var task=$("#task").val();
        var snCode=$("#snCode").val();
        $.ajax({
            url:"/meterialList",
            type:"POST",
            data:{
                "task":task,
                "snCode":snCode
            },
            beforeSend:function(obj){
                layer.load(1);
            },
            success:function (res) {
                if (res.status=200){
                    layer.closeAll('loading');
                    $("#snT tbody").empty();
                    var data=res.body;
                    $.each(data,function (index, item) {
                        //序号
                        var check = $("<td></td>");
                        //Id
                        var id = $("<td></td>").append(item.id);
                        //物料编号
                        var materielCode = $("<td></td>").append(item.materielCode);
                        //任务
                        var task = $("<td></td>").append(item.task);
                        //箱号
                        var boxCode = $("<td></td>").append(item.boxCode);
                        //sn编码
                        var snCode = $("<td></td>").append(item.snCode);
                        //备料号
                        var spareCode = $("<td></td>").append(item.spareCode);
                        //属性号
                        var attributeCode = $("<td></td>").append(item.attributeCode);
                        //打印人
                        var printer = $("<td></td>").append(item.printer);
                        //打印时间
                        var printDate = $("<td></td>").append(item.printDate);
                           //打印次数
                        var lastPrinter = $("<td></td>").append(item.lastPrinter);
                           //最后一次打印人
                        var printTimes = $("<td></td>").append(item.printTimes);
                           //最以后一次打印时间
                        var lastPrintDate = $("<td></td>").append(item.lastPrintDate);
                            //最后一次打印次数
                        var lastPrintTime = $("<td></td>").append(item.lastPrintTime);
                            //创建时间
                        var createDate = $("<td></td>").append(item.createDate);
                        $("<tr></tr>").append(check).append(id).append(materielCode).append(task).append(boxCode).append(snCode).append(spareCode).append(attributeCode)
                            .append(attributeCode).append(printer).append(printDate).append(lastPrinter).append(printTimes).append(lastPrintDate).append(lastPrintTime).append(createDate).appendTo("#snT tbody");
                    })
                    
                }
            }
        })

    }

</script>

</body>
</html>
