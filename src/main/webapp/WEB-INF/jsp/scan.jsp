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
                <li class="layui-nav-item ">
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
                <input class="layui-input" name="snCode" id="snCode" autocomplete="off">
            </div>
            <button class="layui-btn" onclick="toScan()">搜索</button>
        </div>
        <table class="layui-table" id="snT" lay-filter="">
            <thead>
            <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
            <th>物料编码</th>
            <th>任务</th>
            <th>箱号</th>
            <th>sn编码</th>
            <th>备料号</th>
            <th>属性号</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}">操作</th>
            </thead>
            <tbody>

            </tbody>

        </table>
    </div>

    <div id="pages" class="center"></div>

    <div class="layui-row" id="popUpdateTest" style="display:none;">
        <div class="layui-col-md10">
            <form class="layui-form layui-from-pane"  method="post" id="editForm"  style="margin-top:20px" >
                <div class="layui-form-item">
                    <label class="layui-form-label">物料编码</label>
                    <div class="layui-input-block">
                        <input type="text" name="materielCode"  required  lay-verify="required" autocomplete="off" placeholder="物料编码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">任务</label>
                    <div class="layui-input-block">
                        <input type="text" name="task"  required  lay-verify="required" autocomplete="off" placeholder="任务" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">箱号</label>
                    <div class="layui-input-block">
                        <input type="text" name="boxCode"  required  lay-verify="required" autocomplete="off" placeholder="箱号" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">sn序列号</label>
                    <div class="layui-input-block">
                        <input type="text" name="snCode"  required  lay-verify="required" autocomplete="off" placeholder="sn序列号" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备料号</label>
                    <div class="layui-input-block">
                        <input type="text" name="spareCode"  required  lay-verify="required" autocomplete="off" placeholder="备料号" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">属性号</label>
                    <div class="layui-input-block">
                        <input type="text" name="attributeCode"  required  lay-verify="required" autocomplete="off" placeholder="属性号" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top:40px">
                    <div class="layui-input-block">
                        <button class="layui-btn" onclick="commitEdit()" id="tijiao">确认修改</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
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

    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    function commitEdit() {
        $.ajax
        ({ //请求登录处理页
            type: "post",//请求方式 "POST" 或 "GET"， 默认为 "GET"
            url: "/edit", //登录处理页
            dataType: "json",
            //传送请求数据
            data: $("#editForm").serializeArray(),
            success: function (result) { //登录成功后返回的数据
                if(result.status==200){
                    layer.msg("修改成功", {icon: 6});
                }
            }

        });
        layer.close($("#popUpdateTest"));
        // toScan()

    }



    function deletesn(boxCode,snCode) {
        layer.confirm('真的删除行么', function(index){
            $.ajax({
                url: '/delete?boxCode='+boxCode+'&snCode='+snCode,

                type: 'DELETE',
                success: function(result) {
                    // Do something with the result
                    if(result.status==0){
                        layer.msg("删除成功", {icon: 6});
                    }else {
                        layer.msg("删除失败", {icon: 5});
                    }
                    toScan()
                }
            });
            layer.close(index);
        });
    }

    function editsn(boxCode,snCode) {

        $.ajax({
            type:"POST",
            url:"/meterial/check",
            async:true,
            data:{
                "boxCode":boxCode,
                "snCode":snCode
            },

            success:function (res) {
                if (res.status==200){
                    var data=res.body;
                    //物料编号
                    $("input[name='materielCode']").val(data.materielCode)
                    //任务
                    $("input[name='task']").val(data.task)
                    //箱号
                    $("input[name='boxCode']").val(data.boxCode)

                    //sn编码
                    $("input[name='snCode']").val(data.snCode)

                    //备料号
                    $("input[name='spareCode']").val(data.spareCode)

                    //属性号
                    $("input[name='attributeCode']").val(data.attributeCode)

                }
            }

        });
        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1,
            title: "修改设备信息",
            area: ['420px', '330px'],
            content: $("#popUpdateTest")//引用的弹出层的页面层的方式加载修改界面表单

        });

    }

    function toScan() {
        var boxCode=$("#boxCode").val();
        var snCode=$("#snCode").val();
        $.ajax({
            url:"/meterialList",
            type:"POST",
            data:{
                "boxCode":boxCode,
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
                        //创建时间
                        var createDate = $("<td></td>").append(item.createDate);
                        //修改时间
                        var updateDate = $("<td></td>").append(item.updateDate);

                        $("<tr></tr>").append($("<td></td>")).append(materielCode).append(task).append(boxCode).append(snCode).append(spareCode)
                            .append(attributeCode).append(createDate).append(updateDate).append("<a class=\"layui-btn layui-btn-xs\" onclick= editsn("+item.boxCode+","+item.snCode+")>编辑</a>\n" +
                            "    <a class=\"layui-btn layui-btn-danger layui-btn-xs\" onclick= deletesn("+item.boxCode+","+item.snCode+")>删除</a>").appendTo("#snT tbody");
                    })


                }
            }
        })
    }
</script>

</body>
</html>