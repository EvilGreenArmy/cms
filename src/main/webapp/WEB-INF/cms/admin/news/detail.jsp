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
    editor1.readonly(true);
  });
  $(document).ready(function() {
    $("span[data-name='image']").hide();
    $("span[data-name='multiimage']").hide();
    $("span[data-name='flash']").hide();
    $("span[data-name='media']").hide();
    $("span[data-name='insertfile']").hide();
  });
  function goBack() {
    postDataByURL('${basePath}/news/list.do','','workspace');
  }
</script>
<form >
  <input type="hidden" name="id" value="${news.id}" />
  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
      <li><a href="#">业务管理</a></li>
      <li><a href="#">内容发布</a></li>
      <li><a href="#">内容详情</a></li>
    </ul>
  </div>

  <div class="formbody">

    <div class="formtitle"><span>内容信息</span></div>

    <ul class="forminfo">
      <li><label>标题</label><input name="title" id="title" type="text" value="${news.title}"  readonly class="dfinput" maxlength="100" /><i style="color: red;"></i></li>
      <li><label>类型</label>
      <c:if test="${'1' eq news.type}">
            <input name="type" id="type" type="text" value="创新孵化服务"  readonly class="dfinput" maxlength="100" />
      </c:if>
        <c:if test="${'2' eq news.type}">
          <input name="type" id="type" type="text" value="科技咨询服务"  readonly class="dfinput" maxlength="100" />
        </c:if>
        <c:if test="${'3' eq news.type}">
          <input name="type" id="type" type="text" value="知识产权服务"  readonly class="dfinput" maxlength="100" />
        </c:if>
        <c:if test="${'4' eq news.type}">
          <input name="type" id="type" type="text" value="科技金融服务"  readonly class="dfinput" maxlength="100" />
        </c:if>
        <c:if test="${'5' eq news.type}">
          <input name="type" id="type" type="text" value="找场地"  readonly class="dfinput" maxlength="100" />
        </c:if>
        <c:if test="${'6' eq news.type}">
          <input name="type" id="type" type="text" value="找资金"  readonly class="dfinput" maxlength="100" />
        </c:if>
        <c:if test="${'7' eq news.type}">
          <input name="type" id="type" type="text" value="创新孵化服务"  readonly class="dfinput" maxlength="100" />
        </c:if>
        <c:if test="${'8' eq news.type}">
          <input name="type" id="type" type="text" value="找代理"  readonly class="dfinput" maxlength="100" />
        </c:if>

      </li>
      <li><label>内容</label>
        <textarea name="content" id="content" style="width:800px;height:300px;visibility:hidden;display: block;">${news.content}</textarea>
      </li>
      <li><label>&nbsp;</label><input onclick="goBack();" type="button" class="btn" value="返回"/></li>
    </ul>
  </div>
</form>
