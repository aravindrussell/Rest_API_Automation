Feature: End to End for Tools API

  Background: Generate Token For Authorization
    Given I am an Authorized user

  Scenario: Authorized user is able to add and remove the book
    Given A list of books are available
    When Add my book to my reading list
    Then The Book is added
    When I remove a book from my reading list
    Then Book is removed