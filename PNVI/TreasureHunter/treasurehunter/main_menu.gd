extends Control

@onready var start_button = $StartButton  # Adjust path if needed

func _on_start_button_pressed():
	get_tree().change_scene_to_file("res://scenes/level/ground.tscn")  # Change to your main game scene
