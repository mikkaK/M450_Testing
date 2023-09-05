import {visit} from "@angular/compiler-cli/src/ngtsc/util/src/visitor";

describe('Create new student', () => {
  it('Visits the student creation Form and check if form is accessible', () => {
    cy.visit('/addstudents')
    //cy.contains('List Students').click()
    cy.contains("Name")
    cy.contains("Email")
    cy.contains("Submit")
  })
  it('Fill out the form and submit', () => {
    cy.visit('/addstudents')
    cy.get('#name').type("Testi Mc test")
    cy.get('#email').type("example@example.com")
    cy.get('#submit-btn').click()
  });
})
