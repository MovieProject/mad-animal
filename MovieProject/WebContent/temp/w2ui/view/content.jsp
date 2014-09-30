<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="main">
	<div class="tableContainer">
		<div class="tableRow">
			<div class="boardpage">
				<table id="listtable" class="maintable">
					<tr>
						<td colspan="2">
							<table>
								<tr>
								<td>제목</td><td>루시</td><td rowspan="6">포스터자리</td>
								</tr>
								<tr>
								<td>장르</td><td>SF</td>
								</tr>
								<tr>
								<td>감독</td><td>???</td>
								</tr>
								<tr>
								<td>개봉일</td><td>XX.XX.XX</td>
								</tr>
								<tr>
								<td colspan="2">줄거리</td>
								</tr>
								<tr>
								<td colspan="2">쏼라 쏼라</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							<table>
								<tr>
									<td colspan="3">공지사항</td>
								</tr>
								<c:forEach begin="1" end="5">
									<tr>
										<td>글제목</td><td>작성자</td><td>작성일</td>
									</tr>
								</c:forEach>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td colspan="3">금주 추천</td>
								</tr>
								<c:forEach begin="1" end="5">
									<tr>
										<td>글제목</td><td>작성자</td><td>작성일</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td colspan="3">회원 추천</td>
								</tr>
								<c:forEach begin="1" end="5">
									<tr>
										<td>글제목</td><td>작성자</td><td>작성일</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>