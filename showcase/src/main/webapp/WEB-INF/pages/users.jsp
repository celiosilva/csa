<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <t:language />
   <h2>
      <s:message code="users.titulo" text="Usuários" />
      <a href="users/" class="btn btn-success"><s:message code="a.novo" text="Criar Usuário" /></a>
   </h2>
   <table class="table table-striped table-hover">
      <tr>
         <th><s:message code="users.id" text="Id" /></th>
         <th><s:message code="users.nome" text="Nome" /></th>
         <th><s:message code="users.sexo" text="Sexo" /></th>
         <th><s:message code="users.acoes" text="Ações" /></th>
      </tr>
      <c:forEach var="user" items="${users}">
         <tr>
            <td>${user.id}</td>
            <td>${user.nome}</td>
            <td><s:message code="${user.sexo}" text="${user.sexo.descricao}" /></td>
            <td><f:form method="DELETE" action="users/${user.id}">
                  <div class="btn-group">
                     <a href="users/${user.id}" class="btn btn-info pull-left"><s:message
                           code="btn.detalhes_alterar" text="Detalhar/Alterar" /> </a> <input
                        type="submit" class="btn btn-warning"
                        value="<s:message code="btn.remover" text="Remover"/>"
                        onclick="return confirm('<s:message code="users.remover_confirmacao" text="Deseja realmente remover esse usuário?"/>')" />
                  </div>
               </f:form></td>
         </tr>
      </c:forEach>
   </table>
</t:template>