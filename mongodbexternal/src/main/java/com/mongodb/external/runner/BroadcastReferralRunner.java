package com.mongodb.external.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mongodb.external.entity.BroadcastReferral;
import com.mongodb.external.repository.BroadcastReferralRepository;

@Component
public class BroadcastReferralRunner implements CommandLineRunner {
	
	@Autowired
	private BroadcastReferralRepository broadcastReferralRepository;

	@Override
	public void run(String... args) throws Exception {
		BroadcastReferral broadcastReferral = new BroadcastReferral();
		broadcastReferral.setProviderName("def");
		BroadcastReferral savedBroadcastReferral = broadcastReferralRepository.save(broadcastReferral);
		
		System.out.println(savedBroadcastReferral.getId());
		List list = Arrays.asList("1","2");
		List<BroadcastReferral> referrals = (List<BroadcastReferral>) broadcastReferralRepository.findAllById(list);
		for(BroadcastReferral ref : referrals) {
			System.out.println("referrals = "+ref);
		}
		
	}

}
