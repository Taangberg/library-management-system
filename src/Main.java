void main(){
    LoanPolicy loanPolicy = new StandardLoanPolicy();
    Library library = new Library(loanPolicy);
    ConsoleUI UI = new ConsoleUI(library);
    UI.run();
}