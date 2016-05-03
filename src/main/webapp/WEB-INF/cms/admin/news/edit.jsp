<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
  var editor1;
  KindEditor.ready(function(K) {
    editor1 = K.create('textarea[name="content"]', {
      allowFileManager: false
    });
  });
  $(document).ready(function() {
    $("span[data-name='image']").hide();
    $("span[data-name='multiimage']").hide();
    $("span[data-name='flash']").hide();
    $("span[data-name='media']").hide();
    $("span[data-name='insertfile']").hide();
  });
  function saveNews() {
    if($.trim($("#title").val()) == '') {
      layer.msg('标题不能为空！');
      return;
    }
    var content = editor1.html();
    $("#content").val(content);
    if($.trim(content) == '') {
      layer.msg('内容不能为空！')
      return;
    }
    //保存
    //询问框
    layer.confirm('确定要保存数据吗？', {
      btn: ['确定','取消'] //按钮
    }, function(){
      trimForm('news');
      layer.closeAll();
      postDataByFormName('news','workspace');
    }, function(){
    });
  }
</script>
<form action="${basePath}/news/edit.do" method="post" id="news" name="news">
  <input type="hidden" name="id" value="${news.id}" />
  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
      <li><a href="#">业务管理</a></li>
      <li><a href="#">内容发布</a></li>
      <li><a href="#">新增内容</a></li>
    </ul>
  </div>

  <div class="formbody">

    <div class="formtitle"><span>内容信息</span></div>

    <ul class="forminfo">
      <li><label>标题</label><input name="title" id="title" type="text" value="${news.title}" class="dfinput" maxlength="100" /><i style="color: red;"></i></li>
      <li><label>类型</label>
        <select id="type" name="type" class="select1">
          <option value="1" <c:if test="${'1' eq news.type}">selected="selected"</c:if>>创新孵化服务</option>
          <option value="2" <c:if test="${'2' eq news.type}">selected="selected"</c:if>>科技咨询服务</option>
          <option value="3" <c:if test="${'3' eq news.type}">selected="selected"</c:if>>知识产权服务</option>
          <option value="4" <c:if test="${'4' eq news.type}">selected="selected"</c:if>>科技金融服务</option>
          <option value="5" <c:if test="${'5' eq news.type}">selected="selected"</c:if>>找场地</option>
          <option value="6" <c:if test="${'6' eq news.type}">selected="selected"</c:if>>找资金</option>
          <option value="7" <c:if test="${'7' eq news.type}">selected="selected"</c:if>>找代理</option>
          <option value="8" <c:if test="${'8' eq news.type}">selected="selected"</c:if>>找培训</option>
        </select>
      </li>
      <li><label>内容</label>
        <textarea name="content" id="content" style="width:800px;height:300px;visibility:hidden;display: block;">${news.content}</textarea>
      </li>
      <li><label>&nbsp;</label><input onclick="saveNews();" type="button" class="btn" value="确认保存"/></li>
    </ul>
  </div>
</form>
