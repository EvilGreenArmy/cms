<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript">
  /*$(document).ready(function(){
   $(".click").click(function(){
   $(".tip").fadeIn(200);
   });

   $(".tiptop a").click(function(){
   $(".tip").fadeOut(200);
   });

   $(".sure").click(function(){
   $(".tip").fadeOut(100);
   });

   $(".cancel").click(function(){
   $(".tip").fadeOut(100);
   });

   });*/
  function delSource(){
    layer.confirm('确定要删除数据吗？', {
      btn: ['确定','取消'] //按钮
    }, function(){
      trimForm('newsList');
      $("#newsList").attr("action","${basePath}/news/delete.do")
      layer.closeAll();
      postDataByFormName('newsList','workspace');
    }, function(){
    });
  }

  function checkSelect(){
    var ids = $("input:checked");
    if(ids.size()>0){
      return true;
    }
    return false;
  }

  $(function(){
    $("#chk_all").click(function(){
      $("input[name='id']").prop("checked",$(this).prop("checked"));
    });
  })
  function singleDelete(id) {
    layer.confirm('确定要删除数据吗？', {
      btn: ['确定','取消'] //按钮
    }, function(){
      postDataByURL('${basePath}/news/delete.do',{id:id},'workspace');
      layer.closeAll();
    }, function(){
    });
  }
  function querySource(){
    postDataByFormName('newsList','workspace');
  }
  function preview(val) {
    KindEditor.ready(function(K) {
      var dialog = K.dialog({
        width : 500,
        title : '预览',
        body : val,
        closeBtn : {
          name : '关闭',
          click : function(e) {
            dialog.remove();
          }
        }
      });
    });
  }
</script>
<form action="${basePath}/news/list.do" method="post" id="newsList" name="newsList">
  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
      <li><a href="#">业务管理</a></li>
      <li><a href="#">内容发布</a></li>
    </ul>
  </div>

  <div class="rightinfo">
    <div class="seach_box">

      <fieldset>
        <legend>菜单查询</legend>
        <table>
          <tbody>
          <tr>
            <td class="tabel_title">标题</td>
            <td class="table_cont">
              <input type="text" name="name" placeholder="标题" value="${paramMap.name}" class="search_input"/>
            </td>
            <td  class="tabel_title">&nbsp;</td>
            <td  class="table_cont">
              <%--<input type="text" name="code" placeholder="对应URL" value="${paramMap.code}" class="search_input"/>--%>
              &nbsp;
            </td>
          </tr>
          </tbody>
          <tfoot>
          <tr>
            <td colspan="3"></td>
            <td>
              <button type="button" class="search_btn" onclick="querySource();">查询</button>
            </td>
          </tr>
          </tfoot>
        </table>
      </fieldset>


    </div>
    <div class="tools">

      <ul class="toolbar">
           <li class="click" onclick="getData('${basePath}/news/add.do','','workspace');"><span><img src="${basePath}/img/admin/login/t01.png" /></span>添加</li>
           <li onclick="delSource();"><span><img src="${basePath}/img/admin/login/t03.png" /></span>删除</li>
       </ul>


      <ul class="toolbar1">
        <%--<li><span><img src="${basePath}/img/admin/login/t05.png" /></span>设置</li>--%>
      </ul>

    </div>


    <table class="tablelist">
      <tr>
        <th><input id="chk_all" type="checkbox" value="" /></th>
        <th>标题</th>
        <th>内容</th>
        <th>发布人</th>
        <th>发布时间</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="obj" items="${page.resultList}">
        <tr>
          <td><input name="id" type="checkbox" value="${obj.id}" /></td>
          <td>${obj.title}</td>
          <td><a href="javascript:;" class="tablelink" onclick='preview("${obj.content}");'>预览</a></td>
          <td>${obj.creator.userName}</td>
          <td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
          <td>
            <a href="javascript:;" class="tablelink" onclick="getData('${basePath}/news/edit.do?id=${obj.id}','','workspace');">修改</a> |
            <a href="javascript:;" onclick="singleDelete(${obj.id})" class="tablelink"> 删除</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <jsp:include page="../pagination/ajaxPager.jsp" flush="true" >
      <jsp:param name="formName" value="newsList" />
    </jsp:include>
  </div>
</form>

<script type="text/javascript">
  $('.tablelist tbody tr:odd').addClass('odd');
</script>