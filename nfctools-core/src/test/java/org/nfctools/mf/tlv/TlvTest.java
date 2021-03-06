/**
 * Copyright 2011-2012 Adrian Stabiszewski, as@nfctools.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nfctools.mf.tlv;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

public class TlvTest {

	@Test
	public void testTlvWriter() throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TypeLengthValueWriter writer = new TypeLengthValueWriter(baos);

		writer.write(new byte[] { 0x01, 0x02, 0x03, 0x04 });
		writer.close();

		byte[] writtenData = baos.toByteArray();

		byte[] expectedData = { 0x03, 0x04, 0x01, 0x02, 0x03, 0x04, (byte)0xFE };

		assertArrayEquals(expectedData, writtenData);

	}

	@Test
	public void testTlvReader() throws Exception {

		byte[] data = { 0x03, 0x04, 0x01, 0x02, 0x03, 0x04, (byte)0xFE };
		byte[] expectedData = { 0x01, 0x02, 0x03, 0x04 };
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		TypeLengthValueReader reader = new TypeLengthValueReader(bais);

		assertTrue(reader.hasNext());
		byte[] readData = reader.next();
		assertFalse(reader.hasNext());

		assertArrayEquals(expectedData, readData);
	}

	@Test
	public void testTlvReader2() throws Exception {

		byte[] data = { 0x03, (byte)0xFF, 0x02, (byte)0x99, (byte)0xC1, 0x02, 0x00, 0x00, 0x02, (byte)0x91, 0x53, 0x70,
				(byte)0x91, 0x01, 0x10, 0x54, 0x02, 0x64, 0x65, 0x53, 0x4D, 0x53, 0x20, 0x66, (byte)0xC3, (byte)0xBC,
				0x72, 0x20, 0x64, 0x69, 0x63, 0x68, 0x41, 0x01, 0x00, 0x00, 0x02, 0x76, 0x55, 0x00, 0x73, 0x6D, 0x73,
				0x3A, 0x2B, 0x34, 0x39, 0x31, 0x37, 0x33, 0x33, 0x30, 0x30, 0x31, 0x36, 0x33, 0x39, 0x3F, 0x62, 0x6F,
				0x64, 0x79, 0x3D, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55,
				0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74,
				0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42,
				0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D,
				0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68,
				0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E,
				0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E,
				0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C,
				0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65,
				0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65,
				0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64,
				0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B,
				0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61,
				0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68,
				0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E,
				0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B,
				0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D,
				0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62,
				0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72,
				0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56,
				0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D,
				0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61,
				0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C,
				0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B,
				0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69,
				0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F,
				0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C,
				0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61,
				0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54,
				0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65,
				0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E, 0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63,
				0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65, 0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B,
				0x73, 0x65, 0x68, 0x65, 0x6E, 0x56, 0x69, 0x65, 0x6C, 0x2B, 0x42, 0x6C, 0x61, 0x62, 0x6C, 0x61, 0x2E,
				0x2B, 0x55, 0x6E, 0x64, 0x2B, 0x6D, 0x6F, 0x63, 0x68, 0x2B, 0x6D, 0x65, 0x68, 0x72, 0x2B, 0x54, 0x65,
				0x78, 0x74, 0x2E, 0x2B, 0x4D, 0x61, 0x6C, 0x2B, 0x73, 0x65, 0x68, 0x65, 0x6E, 0x25, 0x32, 0x43, 0x2B,
				0x6F, 0x62, 0x2B, 0x64, 0x61, 0x73, 0x2B, 0x6B, 0x6C, 0x61, 0x70, 0x70, 0x74, 0x2E, (byte)0xFE, 0x00,
				0x00 };
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		TypeLengthValueReader reader = new TypeLengthValueReader(bais);

		assertTrue(reader.hasNext());
		byte[] readData = reader.next();
		assertFalse(reader.hasNext());

		assertEquals(665, readData.length);
	}

	@Test
	public void testLargeData() throws Exception {
		byte[] largeData = new byte[1024];
		byte[] expectedData = new byte[1029];

		expectedData[0] = 0x03;
		expectedData[1] = (byte)0xFF;
		expectedData[2] = 0x04;
		expectedData[3] = 0x00;
		expectedData[1028] = (byte)0xFE;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TypeLengthValueWriter writer = new TypeLengthValueWriter(baos);

		writer.write(largeData);
		writer.close();

		byte[] writtenData = baos.toByteArray();

		assertArrayEquals(expectedData, writtenData);

	}

}
