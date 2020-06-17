package com.tv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tv.models.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
