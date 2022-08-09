Content Playlist Creation

Definitions
Content: This entity represents a piece of content (an asset that has been licensed by a Media company) that is either a movie or an episode of a TV show. A piece of content may have 0 or more ordered Pre-Rolls associated with it.
Pre-Roll: This represents an asset to be played before the content plays (e.g. a video clip that shows "Original Presentation"). A pre-roll may be associated with 0 or more pieces of content. When a customer chooses to play a piece of content, the pre-rolls associated with the content will be played in correct order followed by the content.
Video: This is the actual video file attached to a content or a pre-roll. Each content or pre-roll can have multiple videos attached to it. Each attached video is uniquely tagged with a language, a list of countries.
Playlist: A legal sequence of pre-rolls and content videos in correct order as instructions to a player. The included videos for content and all defined pre-rolls will all have matching attributes (country and language) to form a legal play sequence as defined when capturing the content to pre-roll relationship.
Assignment
Design object models for these.
Define and create models in memory from data defined in a file(in JSON format). Assume large file.
Write a program that will take a content identifier and a country code as input and return matching valid playlists.
We want to understand your thought process and decisions, write these in a readme file as you go, and also describe what production code means to you.
Implement this as a library which can be reused by other applications, treat it as writing production code.
If you have made assumptions, include those in the code comments.
No need to deploy a real db, load these in memory only
Sample Input file:

{
"content": [
{
"name": "MI3",
"preroll": [{ "name": "WB1"}],
"videos": [
{ "name": "V1", "attributes": {"countries": ["US", "UK", "CA"], "language":"English"} },
{ "name": "V2", "attributes": {"countries": ["CA"], "language": "French"} },
{ "name": "V3", "attributes": {"countries": ["US"], "language": "Spanish"} }
]
}
],
"preroll": [
{
"name": "WB1",
"videos": [
{ "name": "V4", "attributes": {"countries": ["US"], "language":"English"} },
{ "name": "V5", "attributes": {"countries": ["CA"], "language": "French"} },
{ "name": "V6", "attributes": {"countries": ["US"], "language": "Spanish"} }
]
}
]
}


Sample Runs:


Input (MI3, UK)

Output

(No legal playlist possible because the Pre-Roll Video isn't compatible with language of Content Video for the US)


Input (MI3, CA)

Output

Playlist1

{V5, V2}


Input (MI3, US)

Output

Playlist1

{V4, V1}

Playlist2

{V6, V3}

