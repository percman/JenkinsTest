	<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<form action="addReimburse.do" method="post">
				<div class="form-group">
					<label for="amount">Amount: </label> <input type="number" step="0.01" min="0.01"
						name="amount" class="form-control" required
						placeholder="Enter the amount">
				</div>
				<div class="form-group">
					<label for="category">Category:</label>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Lodging"> <label class="form-check-label"
							for="Lodging" required>Lodging</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Travel"> <label class="form-check-label"
							for="Travel">Travel</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Food"> <label class="form-check-label" for="Food">Food</label>

					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="category"
							value="Other"> <label class="form-check-label"
							for="Other">Other</label>

					</div>
				</div>
				<div class="form-group"><label for="fileToUpload">Add Receipt Image</labelfor>
				<input id="fileToUpload" name="fileToUpload" type="file">
				</div>
				<div class="button-group">
					<input type="submit" class="btn btn-success" value="Submit">
					<input type="reset" class="btn btn-danger" value="Reset">
				</div>
			</form>
		</div>
	</div>