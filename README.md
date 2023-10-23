# MelodyMap

# Domain:
The project domain our group will deal with is concert events. Our primary objective is to develop a user-friendly
application which allows people to find upcoming dates for their favourite artists based on a specified location. 

# Software Specification
Initially, users pick their desired artist, a related genre artist with a show, or nearby venues. They then select a 
concert based on their location and receive notifications for presale or Ticketmaster ticket purchase.

# User Stories:
As a user, I want to be able to view the upcoming shows in my city. [team story] <br />
As a user, I wanted to be notified when my favourite artists are going on tour and receive the presale date for the tickets. [user story 1] <br />
As a user, I noticed the artist I wanted to see was not playing in a venue near me (city/country), I want to be able to see the other closest venues.[user story 2] <br />
As a user, my favourite artist isn’t performing, I would like to see if any similar artists have any concert dates that are near me. [user story 3] <br />
As a user, I want to view the artist’s concert, but the presale date has already passed. I must be redirected to the general sale. [user story 4] <br />

# Proposed Entities for the Domain:
... change this when finalized
Concert
String name
Name of the concert/tour
Integer[] priceRanges
Gives a range of the prices of the tickets from lowest to highest
String presale (preSaleDateTime)
When presale starts
String generalSale
When presale is closed
String location (country - countryCode eg. CA, US or city or address)
Stores the country code of the concert
String[] upcomingEvents (upcomingEvents)
Stores a list of the upcoming concerts
String artist

