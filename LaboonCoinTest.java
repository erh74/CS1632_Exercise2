import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Re-usable LaboonCoin reference.
    LaboonCoin _l;

    // Create a new LaboonCoin instance before each test.
    @Before
    public void setup() {
	_l = new LaboonCoin();
    }

    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
	assertNotNull(_l);
    }

    // Creating a block String with valid data should return
    // a valid block.  This includes printing data out
    // normally, the previous hash and current hash as a hex
    // representations of themself, and the nonce in hex
    // repretentation.  Values should be delimited by
    // pipes.
    @Test
    public void testCreateBlockValid() {
	int prevHash = 0x0;
	int nonce = 0x16f2;
	int hash = 0x545ac;
	String validBlock = _l.createBlock("kitten", prevHash, nonce, hash);
	assertEquals("kitten|00000000|000016f2|000545ac", validBlock);
    }

    // Trying to represent a blockchain which has no blocks
    // in it should just return an empty string.
    @Test
    public void testGetBlockChainNoElements() {
	// By default, _l.blockchain has no elements in it.
	// So we can just test it immediately.
	String blockChain = _l.getBlockChain();
	assertEquals("", blockChain);
    }


    // Viewing the blockchain as a full string which has valid
    // elements should include all of the elements.  Note that the
    // final element DOES have a trailing \n!
    @Test
    public void testGetBlockChainElements() {
	_l.blockchain.add("TESTBLOCK1|00000000|000010e9|000a3cd8");
	_l.blockchain.add("TESTBLOCK2|000a3cd8|00002ca8|0008ff30");
	_l.blockchain.add("TESTBLOCK3|0008ff30|00002171|0009f908");
	String blockChain = _l.getBlockChain();
	assertEquals("TESTBLOCK1|00000000|000010e9|000a3cd8\nTESTBLOCK2|000a3cd8|00002ca8|0008ff30\nTESTBLOCK3|0008ff30|00002171|0009f908\n", blockChain);
    }

    // Testing that a value of null will work as expected returning the hash specifed in the instructions
    // This test should work and return a hash of 00989680
    @Test
    public void testHashNull() {
    int val = _l.hash(null);
    String hex = String.format("%08x",val);
    assertEquals("00989680", hex);
    }

    // Testing that a value of "bill" will work as expected returning the hash specified in the instructions for this input
    // This should work and return a hash of 53c4142c
    @Test
    public void testHashBill() {
    int val = _l.hash("bill");
    String hex = String.format("%08x",val);
    assertEquals("53c4142c", hex);
    }

    // Testing that a string comprised of symbols is hashable
    @Test
    public void testHashInt() {
      int val = _l.hash("9$@#%*@$*&#$kjjdfj   @#$(  193232 =+12~~");
      assertNotNull(val);
    }

    // Test that having a difficulty of 0 works and that a hash with no leading zeroes will be accepted
    @Test
    public void testValidHashDifficulty0() {
      assertTrue(_l.validHash(0, 0x198ab873));
    }

    // Test that a normal difficulty number will work with a normal hash
    // testing with difficulty of 4 and hash with 4 leading zeroes should be valid
    @Test
    public void testValidHashDifficulty4() {
      assertTrue(_l.validHash(4, 0x0000b873));
    }

    // Test that the validHash will still function even if passed a difficulty more than 8
    // testing with a difficulty of 19 so validHash should be false
    @Test
    public void testValidHashDifficulty19() {
      assertFalse(_l.validHash(19, 0x0000b873));
    }




}
