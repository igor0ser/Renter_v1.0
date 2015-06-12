
<div class="title-container">
	<div id="title-string">RENTER</div>
	<form action="controller" method="post" name="command">
		<input type="hidden" name="command" value="change_language" />
		<div id="language">
			<button name="lang" value="en" type="submit">EN</button>
			</br>
			<button name="lang" value="ru" type="submit">RU</button>
			</br>
		</div>
	</form>

	<div id="menu">
		<table>
			<tbody>
				<tr>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="created_apps" />
							<button type="submit"><fmt:message key="Created_apps" /></button>
						</form>
					</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="assigned_apps" />
							<button type="submit"><fmt:message key="Assigned_apps" /></button>
						</form>
					</td>
					<td>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="logout" />
							<button type="submit"><fmt:message key="Log_out" /></button>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>





</body>