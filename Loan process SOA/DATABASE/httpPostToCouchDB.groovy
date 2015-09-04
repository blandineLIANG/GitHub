import groovy.json.JsonSlurper
import groovy.json.StreamingJsonBuilder


def customer = [
	id: id,
	firstName: first_name,
	lastName: last_name,
	loanType: loan_type,
	loanAmount: loan_amount,
	checkNumber:check_number,
	loanRisk: loan_risk,
	loanDate: loan_date,
	loanDescription: loan_description,
	result: transfer_validation
]

def url = new URL("http://127.0.0.1:5984/loan_archive/")

def customer_resp = url.openConnection().with {
	requestMethod = "POST"
	doOutput = true
	setRequestProperty("Content-Type", "application/json;charset=UTF-8")
	outputStream.withWriter("UTF-8") { new StreamingJsonBuilder(it, customer) }
	new JsonSlurper().parse(inputStream.newReader("UTF-8"))
}