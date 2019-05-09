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
                <input class="layui-input" name="task" id="1" autocomplete="off">
            </div>
            Sn序列号：
            <div class="layui-inline">
                <input class="layui-input" name="snCode" id="2" autocomplete="off">
            </div>
            <button class="layui-btn">搜索</button>
        </div>
        <table class="layui-table" id="snT" lay-filter="">
            <%--  <thead>
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
              </thead>--%>
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

<script>
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
            , exts: 'xls|xlsx' //只允许上传压缩文件
            ,before:function (obj) {
                layer.load(2);
            }
            , done: function (res) {
                if (res.status = 201) {
                    layer.closeAll('loading');
                    layer.msg("导入成功！", {icon: 6, time: 1000,});
                    layui.use('table', function () {
                        var table = layui.table;
                        table.render({
                            elem: '#snT'
                            , cols: [[
                                {type: 'checkbox', fixed: 'left'}
                                , {field: 'id', title: 'ID', fixed: 'left', unresize: true, sort: true}
                                , {field: 'materielCode', title: '物料编码', edit: 'text'}
                                , {field: 'task', title: '箱号', edit: 'text'}
                                , {field: 'snCode', title: '属性号', edit: 'text', sort: true}
                                , {field: 'printer', title: '打印人'}
                                , {field: 'print_date', title: '打印时间'}
                                , {field: 'print_times', title: '打印次数', sort: true}
                                , {field: 'last_printer', title: '最后一次打印人'}
                                , {field: 'last_print_date', title: '最以后一次打印时间'}
                                , {field: 'last_print_time', title: '最后一次打印次数'}
                                , {field: 'create_date', title: '创建时间'}
                                , {fixed: 'right',title: '操作', toolbar: '#barDemo'}
                            ]],
                            data: res.body
                        });
                    });
                }
            }
            ,error: function(index, upload){
                layer.closeAll('loading'); //关闭loading
            }
        });
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

</script>

</body>
</html>
