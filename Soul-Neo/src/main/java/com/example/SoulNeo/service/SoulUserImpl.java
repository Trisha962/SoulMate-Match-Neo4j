package com.example.SoulNeo.service;

import com.example.SoulNeo.domain.User;


import com.example.SoulNeo.repository.InterestRepository;
import com.example.SoulNeo.repository.MatchRepository;
import com.example.SoulNeo.repository.SoulInterfaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class SoulUserImpl implements ISoulUser{
    private SoulInterfaceRepository soulInterfaceRepository;
    private MatchRepository matchRepository;
    private InterestRepository interestRepository;
    @Autowired
    public SoulUserImpl(SoulInterfaceRepository soulInterfaceRepository, MatchRepository matchRepository, InterestRepository interestRepository) {
        this.soulInterfaceRepository = soulInterfaceRepository;
        this.matchRepository = matchRepository;
        this.interestRepository = interestRepository;
    }

    @Override
    public void createLikeRelationship(String userEmail1, String userEmail2) {
        interestRepository.createLikeRelationship(userEmail1,userEmail2);
    }
    @Override
    @Transactional
    public void unlikeUser(User userFrom, User userTo) {
        interestRepository.deleteLikeRelationship(userFrom.getEmail(),userTo.getEmail());
    }


//@Override
//public void unlikeUser(User userFrom, User userTo) {
//    interestRepository.deleteByUserFromAndUserTo(userFrom, userTo);
//}
//
@Override
public List<User> findMatches(String userEmail) {
    return matchRepository.findMatches(userEmail);
}
    @Override
    public User getUserById(String userFromId) {
        Optional<User> userOptional = soulInterfaceRepository.findById(userFromId);
        return userOptional.orElse(null);
    }

//    @Override
//    public User findMatch(String userEmail) {
//        return matchRepository.findMatch(userEmail);
//    }
    @Override
    public List<User> getUsersByCity(String city) {
        return (List<User>) soulInterfaceRepository.findByCity(city);
    }
    @Override
    public Collection<User> getAll() {
        return soulInterfaceRepository.getAllUsers();
    }

    @Override
    public User saveUser(User user) {

        return soulInterfaceRepository.save(user);
    }

    @Override
    public List<User> findByGenderNot(String gender) {
        return soulInterfaceRepository.findByGenderNot(gender);
    }




}
