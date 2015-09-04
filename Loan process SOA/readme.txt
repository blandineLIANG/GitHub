Project SOA: loan process
author:Xinyue LIANG
---------------------------------------------------
1 -----  
The Bonita workflow contains 5 pools: Customer, Financial Server, Partner Service, Bank and Provider

Partner Service: analyse the risk (REST)
Bank Service: validate the check (WS-SOAP)
Provider: transaction simulation (BPEL)
financial service: archive the customer information (request http post to CouchDB rest API)

2 -----
a)Start the ode, add the BPEL process "Transaction" and "TransactionInvoke" in the dossier "ode" to the ode server. (Eclipse)
b)Start the tomcat, add the projet "WS_loan" to the server Tomcat as a web application. (Eclipse)
c)Start the REST service. run as application (Eclipse)
d)Start the Bonita and run the project.
e)The customer data will be stored in CouchDB with format JSON

3 -----
CouchDB  http://couchdb.apache.org
CouchDB is a database that completely embraces the web. Store the data with JSON documents. Access the documents and query the indexes with web browser, via HTTP. Index, combine, and transform the documents with JavaScript. 

4 -----
The project “SCA_BPEL” doesn’t work.