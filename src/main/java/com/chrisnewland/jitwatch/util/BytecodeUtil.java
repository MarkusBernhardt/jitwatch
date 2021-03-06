/*
 * Copyright (c) 2013, 2014 Chris Newland.
 * Licensed under https://github.com/AdoptOpenJDK/jitwatch/blob/master/LICENSE-BSD
 * Instructions: https://github.com/AdoptOpenJDK/jitwatch/wiki
 */
package com.chrisnewland.jitwatch.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chrisnewland.jitwatch.model.IMetaMember;

public class BytecodeUtil
{
	// TODO fetch bytecode descriptions from
	// http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.if_icmp_cond

	public static String getBytecodeForMember(IMetaMember member, List<String> classLocations)
	{
		String bytecodeSignature = member.getSignatureForBytecode();
		Map<String, String> bytecodeCache = member.getMetaClass().getBytecodeCache(classLocations);

		String result = bytecodeCache.get(bytecodeSignature);

		if (result == null)
		{
			List<String> keys = new ArrayList<>(bytecodeCache.keySet());

			bytecodeSignature = ParseUtil.findBestMatchForMemberSignature(member, keys);

			if (bytecodeSignature != null)
			{
				result = bytecodeCache.get(bytecodeSignature);
			}
		}

		return result;
	}
}
