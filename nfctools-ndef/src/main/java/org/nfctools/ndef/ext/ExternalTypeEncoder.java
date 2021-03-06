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
package org.nfctools.ndef.ext;

import org.nfctools.ndef.NdefConstants;
import org.nfctools.ndef.NdefEncoderException;
import org.nfctools.ndef.NdefMessageEncoder;
import org.nfctools.ndef.NdefRecord;
import org.nfctools.ndef.Record;
import org.nfctools.ndef.wkt.encoder.RecordEncoder;

public class ExternalTypeEncoder implements RecordEncoder {

	@Override
	public boolean canEncode(Record record) {
		return record instanceof ExternalTypeRecord;
	}

	@Override
	public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder) {
		ExternalTypeRecord externalType = (ExternalTypeRecord)record;
		
		if(!externalType.hasNamespace()) {
			throw new NdefEncoderException("Expected namespace", record);
		}
		if(!externalType.hasContent()) {
			throw new NdefEncoderException("Expected content", record);
		}
		
		byte[] type = externalType.getNamespace().getBytes(NdefConstants.DEFAULT_CHARSET);
		byte[] paylod = externalType.getContent().getBytes(NdefConstants.DEFAULT_CHARSET);
		return new NdefRecord(NdefConstants.TNF_EXTERNAL_TYPE, type, record.getId(), paylod);
	}
}
