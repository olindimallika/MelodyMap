# MelodyMap


# Domain
The project domain our group will deal with is concert events. Our primary objective is to develop a user-friendly
application which allows people to find upcoming dates for their favourite artists based on a specified location.


# Software Specification
Initially, users pick their desired artist, a related genre artist with a show, or nearby venues. They then select a
concert based on their location and receive notifications for presale or Ticketmaster ticket purchase.


# User Stories:
As a user, I want to see upcoming shows in a user’s city. [team story] <br />
As a user, I wanted to be notified user when my favourite artists are going on tour. [user story 1] <br />
As a user, I want to given the presale date for tickets.[user story 2] <br />
As a user, I want to see other closest venues for my favourite artist, if they are not in the same city (but still in Canada). [user story 3] <br />
As a user, I want to see other near concert dates for a similar artist. [user story 4] <br />
As a user, I want to go to the general sale after the presale period is over. [user story 5] <br />




# Proposed Entities for the Domain:
Note: descriptions will be added in later...
- Interface Artist
- String getName()
- String getGenre()
- String getArtistId()
  <br />
  <br />
- Class ArtistModel
- private String name
- private String genre;
- private String artistId
  <br />
  <br />
- Interface User
- String getName()
- String[] getFavArtist()
- String getLocation()
  <br />
  <br />
- Class UserModel
- private String name
- private String[] favouriteArtist
- private String postalCode
- private String location


# Core Use Case
As a user, I want to be presented with upcoming shows in my city.

# UML Diagram
![](/Users/olindimallika/Downloads/uml_so_far.jpeg)