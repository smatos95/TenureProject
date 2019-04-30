/*
 * Updates the character count associated with the text area.
 * @param {String} currentLabelId: The Id of the label that shows the current char count.
 * @param {String} textFieldId: The Id of the text field. Used for retrieving the length to update the currentLabelId  
 *          |   Shane Panagakos (2019)
 * @author  |   Steven Matos (2019)
 */
function updateCharCount(currentLabelId, textFieldId)
{
        var currentCountLabel = document.getElementById(currentLabelId),
        lengthOfText = document.getElementById(textFieldId).value.length;
        currentCountLabel.innerHTML = lengthOfText;
} 
function updateTeachingNarrativeCharCount()
{
        var currentCountLabel = document.getElementById("teachingNarrativeCurrent"),
        lengthOfText = document.getElementById("teachingNarrativeTextarea").value.length;
        currentCountLabel.innerHTML = lengthOfText;
}
function updateScholarlyGrowthNarrativeCharCount()
{
        var currentCountLabel = document.getElementById("narrativeOnContinuingScholarlyGrowthCurrent"),
        lengthOfText = document.getElementById("narrativeOnContinuingScholarlyGrowthTextarea").value.length;
        currentCountLabel.innerHTML = lengthOfText;
}
function updateUniCommNarrativeCharCount()
{
        var currentCountLabel = document.getElementById("uniCommnarrativeCurrent"),
        lengthOfText = document.getElementById("uniCommnarrativeTextarea").value.length;
        currentCountLabel.innerHTML = lengthOfText;
}
   
        
