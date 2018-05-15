$(document).ready(() =>{
	ajax()
	
	$('#pending').click(pending)
	$('#resolved').click(resolved)
	$('#all').click(() => {
		$('table > tbody').empty()
		ajax()
	})

})

let ajax = () => {
	let xhr = new XMLHttpRequest()
	let url = 'http://localhost:8080/ers/JacksonEmployeeReimbursementServlet'
	xhr.open('get', url)
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			let reimbursements = JSON.parse(xhr.responseText)
			for(let r of reimbursements){
				let row = '<tr>'
					row += `<td>${r.reimbursementid}</td>`
					row += `<td>${r.status}</td>`
					row += `<td>${r.image}</td>`
					row += `<td>${r.category}</td>`
					row += '</tr>'
				$('table > tbody:last-child').append(row)
			}
		}
	}
	xhr.send()
}

let pending = () =>{
	$('table > tbody').empty()
	let xhr = new XMLHttpRequest()
	let url = 'http://localhost:8080/ers/JacksonEmployeeReimbursementServlet'
	xhr.open('get', url)
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			let reimbursements = JSON.parse(xhr.responseText)
			for(let r of reimbursements){
				if(r.status !== 'pending')
					continue
				
				let row = '<tr>'
					row += `<td>${r.reimbursementid}</td>`
					row += `<td>${r.status}</td>`
					row += `<td>${r.image}</td>`
					row += `<td>${r.category}</td>`
					row += '</tr>'
				$('table > tbody:last-child').append(row)
			}
		}
	}
	xhr.send()
}

let resolved = () => {
	$('table > tbody').empty()
	let xhr = new XMLHttpRequest()
	let url = 'http://localhost:8080/ers/JacksonEmployeeReimbursementServlet'
	xhr.open('get', url)
	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			let reimbursements = JSON.parse(xhr.responseText)
			for(let r of reimbursements){
				if(r.status === 'pending')
					continue
				
				let row = '<tr>'
					row += `<td>${r.reimbursementid}</td>`
					row += `<td>${r.status}</td>`
					row += `<td>${r.image}</td>`
					row += `<td>${r.category}</td>`
					row += '</tr>'
				$('table > tbody:last-child').append(row)
			}
		}
	}
	xhr.send()
}