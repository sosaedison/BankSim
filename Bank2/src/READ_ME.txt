Our test cases are supposed to test the various functionalities of our simulation. These functionalities include, giving the total elapsed
time, the average customer wait time and the number of total customers helped.

The format is as follows:
Test_case_title 

followed by:
A Test_case description

Idle_Time_Test:
This test case is supposed to test our idleTime counter and fuctionality for each teller. We think it's important to test the
idleTime because the more idleTime a teller has, the more it confirms that we have too many tellers. 

LongList:
We have several of these kinds of tests. They simply insure that all functionality is operational with different types of events;
walkIns or driveIns. This is just a long list of random customers from the sample_data file just to test if our code can handle time jumps
and order them appropriately. 

same_entry_and_exits
This test case was built in order to test the ordering of our priorityqueue. WalkIns at the same eventTime follow driveIns at the same eventTime
and then we had to test a mic of the two.

shorterList
This was a test case much like LongList in that we cut several hundred lines from the sampleData file in order to be able to better make sense of 
any errors in our code. This test_case is comprised of random customers from the sampleData file.

sml_alldt
This is a small test case of all driveThru customers. This was meant to test the functionality of our driveThru line and make sure that our tellers
were able to resonably attend the driveThru "window".

sml_allwalk
This was a small test case of all walkIns as part of a thorough test in teller. This will robustly test the methods in teller, making sure 
they can handle the walkIn customers properly. 

teller_line_test
This is a test case for the teller lines. We wanted to make sure that the teller lines were building properly as we had a few issues
with one teller getting signifantly more customers than the other tellers. 

veryshortlist
Again, in a effort to break our code, we threw several walkIn into the bank, at different times to see if we could catch any bugs in the departure
creation and how it was ordered in the PriorityQueue.
