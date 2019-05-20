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
                    <a  href="/page/import">Sn数据处理</a>
                </li>
                <li class="layui-nav-item layui-this">
                    <a  href="/page/toScan">设备管理</a>
                </li>
                <li class="layui-nav-item ">
                    <a  href="/page/toWork">SN工作台</a>
                </li>
            </ul>
        </div>
    </div>


    <div class="layui-body">

        <div class="demoTable">
            箱号：
            <div class="layui-inline">
                <input class="layui-input" name="boxCode" id="boxCode"  autocomplete="off">
            </div>
            Sn序列号：
            <div class="layui-inline">
                <input class="layui-input" name="snCode" id="snCode" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>
        <div class="layui-btn-group demoTable">
            <button class="layui-btn" data-type="batchDelete">删除选择数据</button>
            <button type="button" class="layui-btn" id="importFile"><i class="layui-icon"></i>上传文件</button>
            <button class="layui-btn"  data-type="exportExcel">导出数据</button>
        </div>
        <table class="layui-table" id="snT" lay-filter="demo">

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
                <div class="layui-form-item">
                    <label class="layui-form-label">电源线料号</label>
                    <div class="layui-input-block">
                        <input type="text" name="powerCode"  required  lay-verify="required" autocomplete="off" placeholder="电源线料号" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">产品名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="productName"  required  lay-verify="required" autocomplete="off" placeholder="产品名称" class="layui-input">
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
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/javascript">
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
    layui.use('upload', function () {
        var $ = layui.jquery
            , upload = layui.upload;
        upload.render({ //允许上传的文件后缀
            elem: '#importFile'
            , url: '/sncheck/upload'
            , accept: 'file' //普通文件
            , exts: 'xls|xlsx' //只允许上传excel文件
            ,before:function (obj) {
                layer.load(1);
            }
            , done: function (res) {
                if (res.status == 201) {
                    layer.closeAll('loading');
                    layer.msg("导入成功！", {icon: 6, time: 1000,});
                    location.reload();
                }
            }
            ,error: function(index, upload){
                layer.closeAll('loading'); //关闭loading
            }
        });
    });

    layui.use('table', function(){
        var table = layui.table;

        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url: '/delete?boxCode='+data.boxCode+'&snCode='+data.snCode,
                        type: 'DELETE',
                        success: function(result) {
                            // Do something with the result
                            if(result.status==0){
                                layer.msg("删除成功", {icon: 6});
                                obj.del();
                            }else {
                                layer.msg("删除失败", {icon: 5});
                            }
                        }
                    });
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
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

                //电源线料号
                $("input[name='powerCode']").val(data.powerCode)

                //产品名称
                $("input[name='productName']").val(data.productName)

                layer.open({
                    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    type: 1,
                    title: "修改设备信息",
                    area: ['500px', '400px'],
                    content: $("#popUpdateTest")//引用的弹出层的页面层的方式加载修改界面表单

                });
            }
        });
        table.render({
            elem: '#snT'
            ,url:'/meterialList'
            ,toolbar: true
            ,title: '产品入库表'
            ,totalRow: true
            ,height: 'full-200'
            ,cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'boxCode', title: '箱号'}
                    , {field: 'snCode', title: 'sn序列号'}
                    , {field: 'materielCode', title: '物料编码', edit: 'text'}
                    , {field: 'productName', title: '产品名称', edit: 'text', sort: true}
                    , {field: 'powerCode', title: '电源料号', edit: 'text', sort: true}
                    , {field: 'attributeCode', title: '属性号', edit: 'text', sort: true}
                    , {field: 'task', title: '任务', edit: 'text', sort: true}
                    , {field: 'spareCode', title: '备料单号', edit: 'text', sort: true}
                    , {field: 'num', title: '入库数', edit: 'text', sort: true}
                    , {field: 'updateDate', title: '更新时间', sort: true}
                    , {field: 'createDate', title: '创建时间',sort: true}
                    , {fixed: 'right',title: '操作', toolbar: '#barDemo'}
                ]]
            ,page: true
            ,response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            ,parseData: function(res){
                // var data=res.body;
                // var reslt,total;
                // //将原始数据解析成 table 组件所规定的数据
                // $.each(data,function (index, item){
                //     reslt=it
                // });
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.body.pageInfo.totalElement, //解析数据长度
                    "data": res.body.results //解析数据列表
                };
            }
        });
        var $ = layui.$, active = {
            batchDelete: function(){ //删除选中数据
                var checkStatus = table.checkStatus('snT')
                    ,data = checkStatus.data,boxCodes="",snCodes="";
                layer.confirm('真的删除删除所选行吗', function(index){
                    $.each(data,function (index, item) {
                        if(index==0){
                            boxCodes+="boxCodes="+item.boxCode;
                            snCodes+="&snCodes="+item.snCode;
                        }else {
                            boxCodes+="&boxCodes="+item.boxCode;
                            snCodes+="&snCodes="+item.snCode;
                        }
                    })
                    $.ajax({
                        url: '/batchDelete?'+boxCodes+snCodes,
                        type: 'delete',
                        success: function(result) {
                            // Do something with the result
                            if(result.status==0){
                                layer.msg("删除成功", {icon: 6});
                                location.reload();
                            }else {
                                layer.msg("删除失败", {icon: 5});
                            }
                        }
                    });
                    layer.close(index);
                });

            }
            ,exportExcel: function(){ //验证是否全选

                const a = document.createElement('a'); // 创建a标签
                a.setAttribute('download', '');// download属性
                a.setAttribute('href', '/sncheck/excelExport?boxCode='+$('#boxCode').val()+'&snCode='+$('#snCode').val());
                console.log('/sncheck/excelExport?boxCode='+$('#boxCode').val()+'&snCode='+$('#snCode').val())// href链接
                a.click();// 自执行点击事件
            }
            ,reload: function(){
                var boxCode = $('#boxCode');
                var snCode = $('#snCode');
                table.reload('snT', {
                    where: {
                        boxCode: boxCode.val(),
                        snCode : snCode.val()
                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
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
            url: "/edit", //编辑修改
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
    };
</script>

</body>
</html>