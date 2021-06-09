$(function () {
    $("#jqGrid").jqGrid({
        url: '/user/history/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'historyId', index: 'historyId', width: 50, key: true, hidden: true},
            {label: '博客标题', name: 'blogTitle', index: 'blogTitle', width: 120, },
            {label: '访问入口', name: 'blogId', index: 'blogId', width: 120, formatter: blogUrl},
            {label: '游览时间', name: 'createTime', index: 'createTime', width: 60},
        ],
        height: 700,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
    function blogUrl(cellvalue) {
        return "<a href='/blog/"+cellvalue+"'>"+"去游览"+"</a>";
    }

});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

