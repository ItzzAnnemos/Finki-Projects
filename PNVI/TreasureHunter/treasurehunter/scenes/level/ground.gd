extends StaticBody3D

var score: int = 0  # Tracks total score

@onready var score_label = $UI/ScoreLabel  # Adjust this path to match your UI

func _ready():
	# Ensure we connect all collectibles at the start
	for collectible in get_tree().get_nodes_in_group("collectibles"):
		collectible.connect("collected", _on_collectible_picked)

func _on_collectible_picked(value: int):
	score += value  # Increase the score
	score_label.text = "Score: " + str(score)  # Update UI
