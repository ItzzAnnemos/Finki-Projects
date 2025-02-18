extends Area3D

signal collected(value: int)  # Signal to send score value

@export var score_value: int = 10  # Change for each collectible type

func _ready():
	connect("body_entered", _on_body_entered)  # Detect collision

func _on_body_entered(body):
	if body.is_in_group("player"):  # Ensure only the player collects it
		collected.emit(score_value)  # Send the score value
		queue_free()  # Remove the collectible from the game
