$(document).ready(() =>{
	let xhr = new XMLHttpRequest()
	let url = 'http://localhost:8080/ers/JacksonEmployeeServlet'
	xhr.open('get', url)
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			let employees = JSON.parse(xhr.responseText)
			for(let e of employees){
				console.log(e)
				let row = '<tr>'
					row += `<td>${e.id}</td>`
					row += `<td>${e.username}</td>`
					row += '</tr>'
				$('table > tbody:last-child').append(row)
			}
		}
	}
	xhr.send()
})