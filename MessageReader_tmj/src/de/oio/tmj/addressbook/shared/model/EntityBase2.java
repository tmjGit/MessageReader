//package de.oio.tmj.addressbook.shared.model;
//
//import java.io.Serializable;
////import java.util.UUID;
//import java.security.*;
//
//public class EntityBase2 implements Serializable{
//	private String guid;
//
//	public EntityBase2() {
//		guid=uUID();//		guid=UUID.randomUUID().toString();
//	}
//
//	
//	
//	
//	/*
//	 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
//	 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
//	 */
//
////	package de.oio.tmj.addressbook.server.service;
//
////	import java.security.*;
//
//	public String getGuid() {
//		return guid;
//	}
//
//
//
//
//		/**
//	 * A class that represents an immutable universally unique identifier (UUID).
//	 * A UUID represents a 128-bit value.
//	 *
//	 * <p> There exist different variants of these global identifiers.  The methods
//	 * of this class are for manipulating the Leach-Salz variant, although the
//	 * constructors allow the creation of any variant of UUID (described below).
//	 *
//	 * <p> The layout of a variant 2 (Leach-Salz) UUID is as follows:
//	 *
//	 * The most significant long consists of the following unsigned fields:
//	 * <pre>
//	 * 0xFFFFFFFF00000000 time_low
//	 * 0x00000000FFFF0000 time_mid
//	 * 0x000000000000F000 version
//	 * 0x0000000000000FFF time_hi
//	 * </pre>
//	 * The least significant long consists of the following unsigned fields:
//	 * <pre>
//	 * 0xC000000000000000 variant
//	 * 0x3FFF000000000000 clock_seq
//	 * 0x0000FFFFFFFFFFFF node
//	 * </pre>
//	 *
//	 * <p> The variant field contains a value which identifies the layout of the
//	 * {@code UUID}.  The bit layout described above is valid only for a {@code
//	 * UUID} with a variant value of 2, which indicates the Leach-Salz variant.
//	 *
//	 * <p> The version field holds a value that describes the type of this {@code
//	 * UUID}.  There are four different basic types of UUIDs: time-based, DCE
//	 * security, name-based, and randomly generated UUIDs.  These types have a
//	 * version value of 1, 2, 3 and 4, respectively.
//	 *
//	 * <p> For more information including algorithms used to create {@code UUID}s,
//	 * see <a href="http://www.ietf.org/rfc/rfc4122.txt"> <i>RFC&nbsp;4122: A
//	 * Universally Unique IDentifier (UUID) URN Namespace</i></a>, section 4.2
//	 * &quot;Algorithms for Creating a Time-Based UUID&quot;.
//	 *
//	 * @since   1.5
//	 */
////	public class UUID implements java.io.Serializable {
//	    /**
//	     * Explicit serialVersionUID for interoperability.
//	     */
//	    private static final long serialVersionUID = -4856846361193249489L;
//
//	    /*
//	     * The most significant 64 bits of this UUID.
//	     * @serial
//	     */
//	    private long mostSigBits;
//
//	    /*
//	     * The least significant 64 bits of this UUID.
//	     * @serial
//	     */
//	    private long leastSigBits;
//
//	    /*
//	     * The random number generator used by this class to create random
//	     * based UUIDs. In a holder class to defer initialization until needed.
//	     */
////	    private static class Holder {
////	        static final SecureRandom numberGenerator = new SecureRandom();
////	    }
//
//	    /**
//	     * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
//	     * The {@code UUID} is generated using a cryptographically strong pseudo
//	     * random number generator.
//	     * @return  A randomly generated {@code UUID}
//	     */
////	    public static UUID randomUUID() {
//	    public String uUID() {
//	    	SecureRandom ng = new SecureRandom();
//
//	        byte[] randomBytes = new byte[16];
//	        ng.nextBytes(randomBytes);
//	        randomBytes[6]  &= 0x0f;  /* clear version        */
//	        randomBytes[6]  |= 0x40;  /* set to version 4     */
//	        randomBytes[8]  &= 0x3f;  /* clear variant        */
//	        randomBytes[8]  |= 0x80;  /* set to IETF variant  */
//	        byte[] data=randomBytes;
//	        long msb = 0;
//	        long lsb = 0;
//	        assert data.length == 16 : "data must be 16 bytes in length";
//	        for (int i=0; i<8; i++)
//	            msb = (msb << 8) | (data[i] & 0xff);
//	        for (int i=8; i<16; i++)
//	            lsb = (lsb << 8) | (data[i] & 0xff);
//	        this.mostSigBits = msb;
//	        this.leastSigBits = lsb;
//	        
//	        return uuidtoString();
//	    }
//
//	    // Object Inherited Methods
//
//	    /**
//	     * Returns a {@code String} object representing this {@code UUID}.
//	     *
//	     * <p> The UUID string representation is as described by this BNF:
//	     * <blockquote><pre>
//	     * {@code
//	     * UUID                   = <time_low> "-" <time_mid> "-"
//	     *                          <time_high_and_version> "-"
//	     *                          <variant_and_sequence> "-"
//	     *                          <node>
//	     * time_low               = 4*<hexOctet>
//	     * time_mid               = 2*<hexOctet>
//	     * time_high_and_version  = 2*<hexOctet>
//	     * variant_and_sequence   = 2*<hexOctet>
//	     * node                   = 6*<hexOctet>
//	     * hexOctet               = <hexDigit><hexDigit>
//	     * hexDigit               =
//	     *       "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
//	     *       | "a" | "b" | "c" | "d" | "e" | "f"
//	     *       | "A" | "B" | "C" | "D" | "E" | "F"
//	     * }</pre></blockquote>
//	     *
//	     * @return  A string representation of this {@code UUID}
//	     */
//	    public String uuidtoString() {
//	        return (digits(mostSigBits >> 32, 8) + "-" +
//	                digits(mostSigBits >> 16, 4) + "-" +
//	                digits(mostSigBits, 4) + "-" +
//	                digits(leastSigBits >> 48, 4) + "-" +
//	                digits(leastSigBits, 12));
//	    }
//
//	    /** Returns val represented by the specified number of hex digits. */
//	    private String digits(long val, int digits) {
//	        long hi = 1L << (digits * 4);
//	        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
//	    }
//
//	}
//
