describe('Fetch Students', () => {
  it('Visits the initial project page and clicks on "List Students" Button ', () => {
    cy.visit('/')
    //cy.contains('List Students').click()
    cy.get('.student-btn').click()
  })
})
