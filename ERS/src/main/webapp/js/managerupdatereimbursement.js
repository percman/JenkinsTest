$(document).ready(() => {
	ajax('all')
	buttons()
	$('#all').click(() => {
		$('table > tbody').empty()
		ajax('all')
	})
})

let employee = function(){
	$('table > tbody').empty()
	let name = $(this).attr('id')
	ajax(name)
}

let buttons = () => {	let xhr = new XMLHttpRequest()
	let url = 'http://localhost:8080/ers/JacksonEmployeeServlet'
	xhr.open('get', url)
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			let employees = JSON.parse(xhr.responseText)
			for(let e of employees){
				let button = `<button class='employee' id='${e.username}'>${e.username}</button>`
				$('#buttons').append(button)
				$('.employee').click(employee)
			}
		}
	}
	xhr.send()
}

let ajax = (user) => {
	let xhr = new XMLHttpRequest()
	let url = `http://localhost:8080/ers/JacksonReimbursementServlet?username=${user}`
	xhr.open('get', url)
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			let reimbursements = JSON.parse(xhr.responseText)
			for(let r of reimbursements){
				let approve = ''
				let reject = ''
				
				switch(r.status){
				case 'pending':
					approve = `<td><a href='ManagerReimbursementServlet?
						status=approved&id=${r.reimbursementid}'>approve</a></td>`
					reject = `<td><a href='ManagerReimbursementServlet?
						status=rejected&id=${r.reimbursementid}'>reject</a></td>`
					break
				case 'approved':
				case 'rejected':
					approve = '<td></td>'
					reject = '<td></td>'
					break
				default:
					approve = '<td></td>'
					reject = '<td></td>'
				}
				
				let row = `<tr>`
					row +=`<td>${r.reimbursementid}</td>`
					row += `<td>${r.employee}</td>`
					row += `<td>${r.manager}</td>`
					row += `<td>${r.category}</td>`
					row += `<td>${r.image}</td>`
					row += `<td>${r.status}</td>`
					row += `${approve}`
					row += `${reject}`
					row += `</tr>`
				$('table > tbody:last-child').append(row)
			}
		}
	}
	xhr.send()	
}
