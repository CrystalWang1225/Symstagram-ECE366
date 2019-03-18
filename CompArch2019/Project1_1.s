/*Brian Chung and Tiffany Yu
--  Project1_1.s --
 Prompts the user to input two strings that are less than 10 characters long.
 If both of the strings are less than 10 characters long, the two strings will be concatenated and printed out.*/
.data

.balign 4
message1:
        .asciz "Please type in something at most 10 characters long: "  /* the first message displayed to prompt the user */

.balign 4
message2:
        .asciz "Please type in something else that is at most 10 characters long: "  /* the second message displayed to prompt the user */

.balign 4
scan_pattern:
        .asciz "%c"  /*scans the input, but only one character at a time */

.balign 4
character_read:
        .word 0

.balign 4
concat: .skip 800 //Ensures there is enough room to store the concatenated string

.balign 4
return:
        .word 0

.balign 4
print:
        .asciz "\nOutput: %c\n" /* prints out an extra line */

.balign 4
printchar:
        .asciz "%c"

.balign 4
printnewline:
        .asciz "\n"

.text
.global main
//initializes any registers needed in the loops and prints out  the  first message
 main:
        mov r8, #0 /* initializes the first counter with zero */
        mov r9, #0 /* initializes the second counter with zero */
        ldr r1, address_of_return /* loads the return address to r1 */
        str lr, [r1]  /* stores the r1 into  a link register */

        ldr r0, address_of_message1  /*loads the address of message 1 to specify what to print */
        bl printf  /* prints out the first message */

        ldr r5, address_of_concat  /*loads the address of concatenated string */
        ldr r6, address_of_concat  /* loads the address of concatenated string again incase the theres an error */

/* the first loop checks if the first inputted string is at most than 10 ASCII characters */
loop:
        add r8, r8, #1 /* increments r8, which is the counter, by one each time the loop is iterated */
        cmp r8, #11 /* comparing r8 to 11 */
        bgt greater /*if r8 is greater than 11, then the loop is broken and the loop greater is run, if not continue with the loop */
        ldr r0, address_of_scan_pattern /*the address of where to place the scanned character is loaded */
        ldr r1, address_of_character_read /* the address of the character that is read is loaded */
        bl scanf /* the character is scanned */

        ldr r0, address_of_print
        ldr r1, address_of_character_read /* loads the address of the character that is read */
        ldr r1, [r1]
        cmp r1, #10 /* compares the scanned characters to a new line*/
        beq prompt2 //If it is a new line, it signifies that it is the end of the string and jumps to the next function
        str r1, [r5], #4 //After  each character is inputed into the array, 4 is added since each ASCII character is 4 bytes it ensures that the next character scanned is considered a new character
        //bl printf
        b loop //repeats the loop
//prints out the second prompt for the user to input another string
prompt2:
        ldr r0, address_of_message2 //loads the address of message2 and sets it up to be printed
        bl printf //prints the second message
//the second loop checks if the second inputted  string is at most than 10 ASCII characters
loop2:
        add r9, r9, #1 // increments r9, which is the counter, by one each time the loop is iterated
        cmp r9, #11 /* comparing r9 to 11 */
        bgt greatersecond //if r9 is greater than 11, then the loop2 will break and the loop greatersecond is run

        ldr r0, address_of_scan_pattern //the address of where to place the scanned character is loaded
        ldr r1, address_of_character_read //the address of the character that is read is loaded
        bl scanf //the character is scanned

        ldr r0, address_of_print
        ldr r1, address_of_character_read //loads the address of the character that is read
        ldr r1, [r1]
        cmp r1, #10 //compares the scanned characters to a new line
        beq prompt3 //If it is a new line, it signifies that it is the end of the string and jumps to the next function
        str r1, [r5], #4 //After  each character is inputed into the array, 4 is added since each ASCII character is 4 bytes it ensures that the next character scanned is considered a new character
        b loop2 //repeats the loop

prompt3:
        str r1, [r5] //storing the register of the array
        mov r6, #0 //initializing r6 with 0
        ldr r5, address_of_concat //loads the address of the register for the concatenated string

printloop:
        add r6, r6, #1 //increments r6, which is a counter, by one each time the printloop is iterated
        cmp r6, #21 //double checks to make sure the concatenated string is at most 20 characters
        beq end //if the concatenated is equal  to to 21, then the loop will exit

        ldr r0, [r5] //loads the register to be printed
        bl putchar //puts out the characeter of the register
        add r5, r5, #4 //After  each character is inputed into the array, 4 is added since each ASCII character is 4 bytes it ensures that the next character scanned is considered a new character
        b printloop //repeats the loop

//prints a new line and the error code and exits the code
end:

        ldr r0, address_of_printnewline //loads the address of printnewline and sets it up to be printed
        bl printf //the new line is printed
        ldr lr, address_of_return //loads the address of return and sets it up to be printed
        ldr lr, [lr] //loads the register r0 to be printed as an exit code
        add r0, r8, r9 //adds the two counters together
        sub r0, r0, #2 //subtracts 2 because an extra 2 was added
        bx lr //exits the code
//in the first loop, if the first inputted string was greater than 10 characters the code would end and print a specific error code
greater:
        mov r0, #21 //moves the number 21 to r0, which is printed as the exit code

        ldr lr, address_of_return //loads the address of return and sets it up to be printed
        ldr lr, [lr] //loads the register r0 to be printed as an exit code
        bx lr //exits the code
//in the second loop, if the second inputted string was greater than 10 characters the code would end and print a specific error code
greatersecond:
        mov r0, #22 //moves the number 22 to r0, which is printed as the exit code

        ldr lr, address_of_return //loads the address of return and sets it up to be printed
        ldr lr, [lr] //loads the register r0 to be printed as an exit code
        bx lr //exits the code

address_of_message1 : .word message1
address_of_message2 : .word message2
address_of_scan_pattern : .word scan_pattern
address_of_character_read : .word character_read
address_of_concat : .word concat
address_of_return : .word return
address_of_print : .word print
address_of_printchar : .word printchar
address_of_printnewline : .word printnewline

.global printf
.global scanf
.global putchar


  

